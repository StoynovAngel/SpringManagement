package com.angel.uni.management.entity.grade;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("BG")
@SuperBuilder
public class GradeBulgarian extends Grade{
    private static final double MIN_MARK = 2.0;
    private static final double MAX_MARK = 6.0;

    @Override
    protected void validateMark(double mark) {
        if (mark < MIN_MARK || mark > MAX_MARK) {
            throw new IllegalArgumentException("BG Grades must be between 2.0 and 6.0");
        }
    }
}