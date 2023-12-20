package com.example.studentmicroservice.service;


import com.example.studentmicroservice.dto.StudentDto;
import com.example.studentmicroservice.model.Student;
import com.example.studentmicroservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;




    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public StudentDto getStudentById(Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            StudentDto studentDto = new StudentDto();
            studentDto.setId(student.get().getId());
            studentDto.setName(student.get().getName());
            studentDto.setEmail(student.get().getEmail());
            return studentDto;
        }
        return null;
    }
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    // Additional methods as needed




}
