package com.courses.modules.student.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class EnrollCourseDTO {
    private UUID idStudent;
    private UUID idCourse;
}
