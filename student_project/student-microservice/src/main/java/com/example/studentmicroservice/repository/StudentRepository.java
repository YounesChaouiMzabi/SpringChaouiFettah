package com.example.studentmicroservice.repository;

import com.example.studentmicroservice.model.Student;
import org.hibernate.boot.jaxb.mapping.JaxbNamedStoredProcedureQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
