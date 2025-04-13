package com.angel.uni.management.controller;

import com.angel.uni.management.dto.GradeDTO;
import com.angel.uni.management.service.GradeService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grade")
public class GradeController {

    private final GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public GradeDTO createGrade(@RequestBody GradeDTO gradeDTO) throws BadRequestException {
        return gradeService.createGrade(gradeDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public GradeDTO getGradeById(@PathVariable("id") Long id) {
        return gradeService.getGradeById(id);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<GradeDTO> getAllGrades() {
        return gradeService.getAllGrades();
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public GradeDTO updateGradeById(@PathVariable("id") Long id, @RequestBody GradeDTO gradeDTO) throws BadRequestException {
        return gradeService.updateGrade(id, gradeDTO);
    }
}
