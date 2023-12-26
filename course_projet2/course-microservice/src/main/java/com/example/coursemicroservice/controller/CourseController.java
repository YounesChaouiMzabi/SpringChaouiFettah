package com.example.coursemicroservice.controller;


import com.example.coursemicroservice.dto.CourseDto;
import com.example.coursemicroservice.model.Course;
import com.example.coursemicroservice.model.MonComposant;
import com.example.coursemicroservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService ;

    @Autowired
    private MonComposant myComponent;

    @GetMapping("/message")
    public String getMessage() {
        return myComponent.getMessage();
    }


    @GetMapping
    public List<Course> getAllCourses() {
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
