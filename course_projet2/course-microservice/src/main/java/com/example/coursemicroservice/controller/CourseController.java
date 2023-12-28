package com.example.coursemicroservice.controller;


import com.example.coursemicroservice.dto.CourseDto;
import com.example.coursemicroservice.model.Course;
import com.example.coursemicroservice.model.MonComposant;
import com.example.coursemicroservice.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
@RefreshScope
public class CourseController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${message}")
    private String message;

    @Autowired
    private CourseService courseService ;

    @Autowired
    private MonComposant myComponent;

    @GetMapping("/message")
    public String getMessage() {
        return this.message;
    }


    @GetMapping
    public List<Course> getAllCourses() {
        log.info("Listing All Users");
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long id) {
        Optional<CourseDto> course = Optional.ofNullable(courseService.getCourseById(id));
        return course.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody CourseDto courseDto) {
        Course createdCourse = courseService.createCourse(
                new Course(courseDto.getTitle(), courseDto.getDescription())
        );
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

//je me suis arreté f la partie RefreshScope
