package com.angel.uni.management.repositories;

import com.angel.uni.management.config.TestDataConfig;
import com.angel.uni.management.data.TestData;
import com.angel.uni.management.entity.UniversityGroup;
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
class UniversityGroupRepositoryTest {

    @Autowired
    private UniversityGroupRepository universityGroupRepository;

    @Autowired
    private TestData testData;

    private UniversityGroup universityGroup;

    @BeforeEach
    void setUp() {
        universityGroup = testData.createUniversityGroup();
    }

    @Test
    void testUniversityGroupRepository_Save() {
        UniversityGroup saved = universityGroupRepository.save(universityGroup);
        assertNotNull(saved);
        Assertions.assertThat(saved.getId()).isGreaterThan(0);
    }
}