package com.angel.uni.management.repositories;

import com.angel.uni.management.config.TestDataConfig;
import com.angel.uni.management.data.TestData;
import com.angel.uni.management.entity.grade.Grade;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(TestDataConfig.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)

class GradeRepositoryTest {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private TestData testData;

    private Grade grade;

    @BeforeEach
    void setUp() {
        grade = testData.createGrade();
    }

    @Test
    void testGradeRepository_Save() {
        Grade saved = gradeRepository.save(grade);
        assertNotNull(saved);
        Assertions.assertThat(saved.getId()).isGreaterThan(0);
    }
}