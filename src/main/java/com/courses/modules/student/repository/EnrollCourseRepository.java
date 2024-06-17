package com.courses.modules.student.repository;

import com.courses.modules.student.entity.EnrollCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EnrollCourseRepository extends JpaRepository<EnrollCourseEntity, UUID> {}
