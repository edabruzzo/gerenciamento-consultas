package br.com.abruzzo.med.voll.security.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan({ "br.com.abruzzo.med.voll.security.task" })
public class SpringTaskConfig {

}
