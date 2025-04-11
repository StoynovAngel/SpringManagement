package com.angel.uni.management.entity;

import com.angel.uni.management.enums.CountryEnum;
import com.angel.uni.management.enums.GradeType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "grade")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @Enumerated(EnumType.STRING)
    @Column(name = "grade_type", nullable = false)
    private GradeType gradeType;

    @Column(name = "mark", nullable = false)
    private double mark;

    @Column(name = "date_of_grading", nullable = false)
    private LocalDateTime dateOfGrading;

    @Column(name = "country", nullable = false)
    private CountryEnum countryRepresentation;
}
