package com.courses.modules.student.controller;

import com.courses.modules.ENUM.StatusEnum;
import com.courses.modules.student.entity.StudentEntity;
import com.courses.modules.student.useCases.StudentUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentUseCase studentUseCase;

    public StudentController(StudentUseCase studentUseCase) {
        this.studentUseCase = studentUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody StudentEntity studentToRegister){
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
}
