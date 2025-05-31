package com.angel.uni.management.repositories;

import com.angel.uni.management.entity.UniversitySubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface USubjectRepository extends JpaRepository<UniversitySubject, Long> {

    UniversitySubject findByName(String name);
}
