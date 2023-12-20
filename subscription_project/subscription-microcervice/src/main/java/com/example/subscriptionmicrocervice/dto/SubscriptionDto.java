package com.example.subscriptionmicrocervice.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class SubscriptionDto {

   private Long id;
    private StudentDto etudiant;
    private CourseDto cours;




}
