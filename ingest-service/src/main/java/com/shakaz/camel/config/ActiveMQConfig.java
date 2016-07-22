package com.shakaz.camel.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.Component;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;

@Configuration
public class ActiveMQConfig {

    @Bean
    ActiveMQConnectionFactory activeMQConnectionFactory() {
        return new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
    }

    @Bean
    Component activemq(ConnectionFactory activeMQConnectionFactory) {
        return JmsComponent.jmsComponentAutoAcknowledge(activeMQConnectionFactory);
    }
}
