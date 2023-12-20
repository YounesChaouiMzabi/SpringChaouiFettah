package com.example.studentmicroservice;

import com.example.studentmicroservice.model.Student;
import com.example.studentmicroservice.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class StudentMicroserviceApplication   {



    public static void main(String[] args) {
        SpringApplication.run(StudentMicroserviceApplication.class, args);

    }





    }

