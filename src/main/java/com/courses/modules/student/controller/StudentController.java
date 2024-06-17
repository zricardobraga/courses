package com.courses.modules.student.controller;

import com.courses.modules.ENUM.StatusEnum;
import com.courses.modules.student.dto.EnrollCourseDTO;
import com.courses.modules.student.entity.StudentEntity;
import com.courses.modules.student.useCases.EnrollCourseStudentUseCase;
import com.courses.modules.student.useCases.StudentUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentUseCase studentUseCase;

    private final EnrollCourseStudentUseCase enrollCourseStudentUseCase;

    public StudentController(StudentUseCase studentUseCase, EnrollCourseStudentUseCase enrollCourseStudentUseCase) {
        this.studentUseCase = studentUseCase;
        this.enrollCourseStudentUseCase = enrollCourseStudentUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody StudentEntity studentToRegister){
        try {
            studentToRegister.setStatus(StatusEnum.ACTIVE);
            var registeredStudent = this.studentUseCase.register(studentToRegister);
            return ResponseEntity.ok(registeredStudent);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/list_all")
    public ResponseEntity<List<StudentEntity>> listAll(){
        List<StudentEntity> studentEntityList = new ArrayList<>();

        studentEntityList = this.studentUseCase.listAll();

        if(studentEntityList.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(studentEntityList);

    }

    @PostMapping("/course/enroll")
    public ResponseEntity<Object> enrollCourse(@RequestBody EnrollCourseDTO enrollCourseDTO){
        try {
            var result = this.enrollCourseStudentUseCase.enroll(enrollCourseDTO.getIdStudent(), enrollCourseDTO.getIdCourse());
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
