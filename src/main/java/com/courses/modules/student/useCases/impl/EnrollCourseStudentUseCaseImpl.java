package com.courses.modules.student.useCases.impl;

import com.courses.exceptions.course.CourseNotFoundException;
import com.courses.modules.course.repository.CourseRepository;
import com.courses.modules.student.entity.EnrollCourseEntity;
import com.courses.modules.student.repository.EnrollCourseRepository;
import com.courses.modules.student.repository.StudentRepository;
import com.courses.modules.student.useCases.EnrollCourseStudentUseCase;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class EnrollCourseStudentUseCaseImpl implements EnrollCourseStudentUseCase {

    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    private final EnrollCourseRepository enrollCourseRepository;

    public EnrollCourseStudentUseCaseImpl(StudentRepository studentRepository, CourseRepository courseRepository, EnrollCourseRepository enrollCourseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrollCourseRepository = enrollCourseRepository;
    }

    @Override
    public EnrollCourseEntity enroll(UUID idStudent, UUID idCourse) {
        this.studentRepository.findById(idStudent).orElseThrow(() -> new NoSuchElementException());

        this.courseRepository.findById(idCourse).orElseThrow(() -> new CourseNotFoundException());

        var enrollCourse = EnrollCourseEntity.builder()
                .studentId(idStudent)
                .courseId(idCourse)
                .build();

        enrollCourse = enrollCourseRepository.save(enrollCourse);


        return enrollCourse;
    }
}
