package com.angel.uni.management.config;

import com.angel.uni.management.data.TestData;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestDataConfig {

    @Bean
    public TestData testData() {
        return new TestData();
    }
}
