package com.courses.modules.student.useCases;

import com.courses.modules.student.entity.EnrollCourseEntity;

import java.util.UUID;

public interface EnrollCourseStudentUseCase {

    EnrollCourseEntity enroll(UUID idStudent, UUID idCourse);



}
