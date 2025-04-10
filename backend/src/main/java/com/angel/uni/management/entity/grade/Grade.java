package com.angel.uni.management.entity.grade;

import com.angel.uni.management.entity.Student;
import com.angel.uni.management.entity.Teacher;
import com.angel.uni.management.enums.GradeType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@SuperBuilder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "grade_format")
public abstract class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

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

    public Grade(String name, Student student, Teacher teacher, GradeType gradeType, double mark, LocalDateTime dateOfGrading) {
        this.name = name;
        this.student = student;
        this.teacher = teacher;
        this.gradeType = gradeType;
        setDateOfGrading(dateOfGrading);
        setMark(mark);
    }

    protected abstract void validateMark(double mark);
    public void setMark(double mark) {
        validateMark(mark);
        this.mark = mark;
    }
}
