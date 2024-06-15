package com.courses.modules.student.useCases;

import com.courses.modules.student.entity.StudentEntity;

import java.util.List;

public interface StudentUseCase {
    StudentEntity register(StudentEntity student);
    List<StudentEntity> listAll();
}
