package com.example.coursemicroservice.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class MonComposant {

    @Value("${message}")
    private String message;

    public String getMessage() {
        return message;
    }
}
