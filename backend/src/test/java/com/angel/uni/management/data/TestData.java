package com.angel.uni.management.data;

import com.angel.uni.management.entity.Student;
import com.angel.uni.management.entity.Teacher;
import com.angel.uni.management.entity.UniversityGroup;
import com.angel.uni.management.entity.UniversitySubject;
import com.angel.uni.management.entity.grade.GradeBulgarian;
import com.angel.uni.management.enums.GradeType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestData {

    private static final int HOURS_PER_WEEK = 10;
    private static final double MARK = 6;

    public Teacher createTeacher() {
        return Teacher.builder()
                .name("teacher")
                .build();
    }

    public Student createStudent() {
        return Student.builder()
                .username("username")
                .grades(new ArrayList<>())
                .build();
    }

    public GradeBulgarian createGrade() {
        return GradeBulgarian.builder()
                .name("grade")
                .student(createStudent())
                .teacher(createTeacher())
                .gradeType(GradeType.FINAL)
                .mark(MARK)
                .dateOfGrading(LocalDateTime.now())
                .build();
    }

    public UniversityGroup createUniversityGroup() {
        return UniversityGroup.builder()
                .groupName("groupName")
                .studentsAssignedToGroup(List.of(createStudent()))
                .build();
    }

    public UniversitySubject createUniversitySubject() {
        return UniversitySubject.builder()
                .name("name")
                .hoursPerWeek(HOURS_PER_WEEK)
                .description("description")
                .teacher(createTeacher())
                .studentsAssignedToSubject(List.of(createStudent()))
                .build();
    }
}
