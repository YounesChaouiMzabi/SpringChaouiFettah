package com.example.coursemicroservice;

import com.example.coursemicroservice.model.Course;
import com.example.coursemicroservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class CourseMicroserviceApplication {
    @Autowired
    private CourseService coursService;

    @Bean
    public CommandLineRunner console() {
        return (args) -> {
            // Ajouter un cours de test lors du démarrage de l'application
            Course course = new Course();
            course.setDescription("programmation OO");
            course.setTitle("POO");

            coursService.createCourse(course);

            // Vous pouvez ajouter d'autres données de test ici
            Course course2 = new Course();
            course2.setDescription("ping pong");
            course2.setTitle("Sport");

            coursService.createCourse(course2);

            Course course3 = new Course();
            course3.setDescription("EVN");
            course3.setTitle("Maths");

            coursService.createCourse(course3);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(CourseMicroserviceApplication.class, args);
    }

}
