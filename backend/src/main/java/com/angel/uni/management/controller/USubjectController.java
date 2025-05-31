package com.angel.uni.management.controller;

import com.angel.uni.management.entity.UniversitySubject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/subject")
public class USubjectController {

    @GetMapping()
    public List<UniversitySubject> getAllSubjects() {
        return null;
    }
}
