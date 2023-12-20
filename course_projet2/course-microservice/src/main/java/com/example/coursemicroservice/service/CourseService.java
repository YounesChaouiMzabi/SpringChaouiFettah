package com.example.coursemicroservice.service;


import com.example.coursemicroservice.dto.CourseDto;
import com.example.coursemicroservice.model.Course;
import com.example.coursemicroservice.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public CourseDto getCourseById(Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            CourseDto courseDto = new CourseDto();
            courseDto.setId(course.get().getId());
            courseDto.setTitle(course.get().getTitle());
            courseDto.setDescription(course.get().getDescription());
            return courseDto;
        }
        return null;
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    // Additional methods as needed
}
