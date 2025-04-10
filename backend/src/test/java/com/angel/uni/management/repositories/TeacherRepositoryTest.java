package com.angel.uni.management.repositories;

import com.angel.uni.management.config.TestDataConfig;
import com.angel.uni.management.data.TestData;
import com.angel.uni.management.entity.Teacher;
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
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TestData testData;

    private Teacher teacher;

    @BeforeEach
    void setUp() {
        teacher = testData.createTeacher();
    }

    @Test
    void testTeacherRepository_Save() {
        Teacher saved = teacherRepository.save(teacher);
        assertNotNull(saved);
        Assertions.assertThat(saved.getId()).isGreaterThan(0);
    }
}