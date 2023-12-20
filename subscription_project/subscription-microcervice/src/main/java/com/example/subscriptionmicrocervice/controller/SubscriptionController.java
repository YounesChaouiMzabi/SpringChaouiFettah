package com.example.subscriptionmicrocervice.controller;

import com.example.subscriptionmicrocervice.CourseClient;
import com.example.subscriptionmicrocervice.StudentClient;
import com.example.subscriptionmicrocervice.dto.CourseDto;
import com.example.subscriptionmicrocervice.dto.StudentDto;
import com.example.subscriptionmicrocervice.dto.SubscriptionDto;
import com.example.subscriptionmicrocervice.model.Subscription;
import com.example.subscriptionmicrocervice.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping()
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final StudentClient studentClient;
    private final CourseClient courseClient;

    @Autowired
    public SubscriptionController(
            SubscriptionService subscriptionService,
            StudentClient studentClient,
            CourseClient courseClient
    ) {
        this.subscriptionService = subscriptionService;
        this.studentClient = studentClient;
        this.courseClient = courseClient;
    }

    @GetMapping("subscriptions")
    public List<SubscriptionDto> getAllSubscriptions() {
        return subscriptionService.getAllInscriptions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable Long id) {
        Optional<Subscription> subscription = subscriptionService.getSubscriptionById(id).blockOptional();
        return subscription.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Subscription> createSubscription(@RequestBody SubscriptionDto subscriptionDto) {
        Subscription subscription = subscriptionService.createSubscription(subscriptionDto);
        return new ResponseEntity<>(subscription, HttpStatus.CREATED);
    }

    @GetMapping("/{studentId}/course")
    public ResponseEntity<CourseDto> getCourseForStudent(@PathVariable Long studentId) {
        StudentDto student = studentClient.getStudentById(studentId);

        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Appel au microservice Course pour récupérer le cours par son ID
        CourseDto course = courseClient.getCourseById(studentId);

        if (course == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(course, HttpStatus.OK);
    }

}
