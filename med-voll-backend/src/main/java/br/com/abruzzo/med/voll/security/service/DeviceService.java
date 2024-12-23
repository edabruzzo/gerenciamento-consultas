package br.com.abruzzo.med.voll.security.service;

import br.com.abruzzo.med.voll.security.persistence.dao.DeviceMetadataRepository;
import br.com.abruzzo.med.voll.security.persistence.model.DeviceMetadata;
import br.com.abruzzo.med.voll.security.persistence.model.Usuario;
import com.google.common.base.Strings;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import ua_parser.Client;
import ua_parser.Parser;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static java.util.Objects.nonNull;

@Component
public class DeviceService {

    private static final String UNKNOWN = "UNKNOWN";

    @Value("${support.email}")
    private String from;

    @Autowired
    private DeviceMetadataRepository deviceMetadataRepository;

    @Autowired
    @Qualifier("GeoIPCity")
    private DatabaseReader databaseReader;

    @Autowired
    private Parser parser;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MessageSource messages;

    public void verifyDevice(Usuario user, HttpServletRequest request) throws IOException, GeoIp2Exception {

        String ip = extractIp(request);
        String location = getIpLocation(ip);

        String deviceDetails = getDeviceDetails(request.getHeader("user-agent"));

        DeviceMetadata existingDevice = findExistingDevice(user.getId(), deviceDetails, location);

        if (Objects.isNull(existingDevice)) {
            unknownDeviceNotification(deviceDetails, location, ip, user.getEmail(), request.getLocale());

            DeviceMetadata deviceMetadata = new DeviceMetadata();
            deviceMetadata.setUserId(user.getId());
            deviceMetadata.setLocation(location);
            deviceMetadata.setDeviceDetails(deviceDetails);
            deviceMetadata.setLastLoggedIn(new Date());
            deviceMetadataRepository.save(deviceMetadata);
        } else {
            existingDevice.setLastLoggedIn(new Date());
            deviceMetadataRepository.save(existingDevice);
        }

    }

    private String extractIp(HttpServletRequest request) {
        String clientIp;
        String clientXForwardedForIp = request.getHeader("x-forwarded-for");
        if (nonNull(clientXForwardedForIp)) {
            clientIp = parseXForwardedHeader(clientXForwardedForIp);
        } else {
            clientIp = request.getRemoteAddr();
        }

        return clientIp;
    }

    private String parseXForwardedHeader(String header) {
        return header.split(" *, *")[0];
    }

    private String getDeviceDetails(String userAgent) {
        String deviceDetails = UNKNOWN;

        Client client = parser.parse(userAgent);
        if (Objects.nonNull(client)) {
            deviceDetails = client.userAgent.family + " " + client.userAgent.major + "." + client.userAgent.minor +
                    " - " + client.os.family + " " + client.os.major + "." + client.os.minor;
        }

        return deviceDetails;
    }

    private String getIpLocation(String ip) throws IOException, GeoIp2Exception {

        String location = UNKNOWN;

        InetAddress ipAddress = InetAddress.getByName(ip);

        CityResponse cityResponse = databaseReader.city(ipAddress);
        if (Objects.nonNull(cityResponse) &&
                Objects.nonNull(cityResponse.getCity()) &&
                !Strings.isNullOrEmpty(cityResponse.getCity().getName())) {

            location = cityResponse.getCity().getName();
        }

        return location;
    }

    private DeviceMetadata findExistingDevice(Long userId, String deviceDetails, String location) {

        List<DeviceMetadata> knownDevices = deviceMetadataRepository.findByUserId(userId);

        for (DeviceMetadata existingDevice : knownDevices) {
            if (existingDevice.getDeviceDetails().equals(deviceDetails) &&
                    existingDevice.getLocation().equals(location)) {
                return existingDevice;
            }
        }

        return null;
    }

    private void unknownDeviceNotification(String deviceDetails, String location, String ip, String email, Locale locale) {
        final String subject = "New Login Notification";
        final SimpleMailMessage notification = new SimpleMailMessage();
        notification.setTo(email);
        notification.setSubject(subject);

        String text = getMessage("message.login.notification.deviceDetails", locale) +
                " " + deviceDetails +
                "\n" +
                    getMessage("message.login.notification.location", locale) +
                " " + location +
                "\n" +
                    getMessage("message.login.notification.ip", locale) +
                " " + ip;

        notification.setText(text);
        notification.setFrom(from);

        mailSender.send(notification);
    }

    private String getMessage(String code, Locale locale) {
        try {
            return messages.getMessage(code, null, locale);

        } catch (NoSuchMessageException ex) {
            return messages.getMessage(code, null, Locale.ENGLISH);
        }
    }

}
