package com.company.capi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * Created by jmbataller on 08/12/14.
 */
@Configuration
public class CustomerConfiguration {

    @Value("${myloyal.endpoint}")
    private String myLoyalEndpoint;

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.company.myloyal");
        return marshaller;
    }

    @Bean
    public CustomerService customerService(Jaxb2Marshaller marshaller) {
        CustomerServiceImpl client = new CustomerServiceImpl();
        client.setDefaultUri(myLoyalEndpoint);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
