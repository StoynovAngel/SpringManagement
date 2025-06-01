package com.angel.uni.management.controller;

import com.angel.uni.management.dto.SubjectDTO;
import com.angel.uni.management.service.UniversitySubjectService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subject")
public class UniversitySubjectController {

    private final UniversitySubjectService subjectService;

    @Autowired
    public UniversitySubjectController(UniversitySubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public SubjectDTO createSubject(@RequestBody SubjectDTO subjectDTO) throws BadRequestException {
        return subjectService.createSubject(subjectDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public SubjectDTO getSubjectById(@PathVariable("id") Long id) {
        return subjectService.getSubjectById(id);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<SubjectDTO> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public SubjectDTO updateSubject(@RequestBody SubjectDTO subjectDTO) throws BadRequestException {
        return subjectService.updateSubject(subjectDTO);
    }
}
