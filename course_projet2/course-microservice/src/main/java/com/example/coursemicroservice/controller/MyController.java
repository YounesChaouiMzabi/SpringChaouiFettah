package com.example.coursemicroservice.controller;

import com.example.coursemicroservice.model.MonComposant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class MyController {

//    @Autowired
//    private MonComposant myComponent;
//
//    @PostMapping("/message")
//    public String getMessage() {
//        return myComponent.getMessage();
//    }
}
