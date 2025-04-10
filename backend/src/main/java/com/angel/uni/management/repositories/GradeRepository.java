package com.angel.uni.management.repositories;

import com.angel.uni.management.entity.grade.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}
