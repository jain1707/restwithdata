package com.example.restwithdata.config;

import com.example.restwithdata.service.ImageUploadS3Service;
import com.example.restwithdata.service.ProductService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestConfiguration extends ResourceConfig {

    public RestConfiguration() {
        register(ProductService.class);
        register(ImageUploadS3Service.class);
    }
}
