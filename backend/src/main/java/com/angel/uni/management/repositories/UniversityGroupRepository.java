package com.angel.uni.management.repositories;

import com.angel.uni.management.entity.UniversityGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityGroupRepository extends JpaRepository<UniversityGroup, Long> {
}
