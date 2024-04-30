package com.urunsiyabend.conferencemanagementsystem.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Local server");

        Contact contact = new Contact();
        contact.setName("Siyabend Ürün");
        contact.setEmail("urunsiyabend@gmail.com");

        Info info = new Info();
        info.setTitle("Conference Management System");
        info.setDescription("This is a simple conference management system.");
        info.setVersion("1.0.0");
        info.setContact(contact);

        OpenAPI openAPI = new OpenAPI();
        openAPI.setInfo(info);
        openAPI.addServersItem(server);

        return openAPI;
    }
}