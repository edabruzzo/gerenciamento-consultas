package br.com.abruzzo.med.voll.security.spring;

import com.maxmind.geoip2.DatabaseReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ResourceUtils;
import ua_parser.Parser;

import java.io.File;
import java.io.IOException;

@Configuration
public class LoginNotificationConfig {

    @Autowired
    private ResourceLoader resourceLoader;

    @Bean
    public Parser uaParser() throws IOException {
        return new Parser();
    }

    @Bean(name="GeoIPCity")
    public DatabaseReader databaseReader() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:maxmind/GeoLite2-City.mmdb");
        return new DatabaseReader.Builder(resource.getInputStream()).build();
    }
}
