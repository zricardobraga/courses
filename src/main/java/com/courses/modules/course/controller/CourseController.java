package com.courses.modules.course.controller;

import com.courses.modules.ENUM.StatusEnum;
import com.courses.modules.course.UseCases.CourseUseCase;
import com.courses.modules.course.entity.CourseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseUseCase courseUseCase;

    public CourseController(CourseUseCase courseUseCase) {
        this.courseUseCase = courseUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register (@RequestBody CourseEntity courseEntity) {
        try {
            courseEntity.setStatus(StatusEnum.ACTIVE);
            var result = this.courseUseCase.register(courseEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/list_all")
    public ResponseEntity<List<CourseEntity>> listAll(){
        List<CourseEntity> courses = new ArrayList<>();
        courses = this.courseUseCase.listAll();
        if(courses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(courses);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody Map<String, Object> data){

        if (data.get("name").toString().isEmpty() || data.get("category").toString().isEmpty()) {
            return ResponseEntity.badRequest().body("Name or Category are empty");
        }

        CourseEntity updatedCourse = this.courseUseCase.update(id, data);

        return ResponseEntity.accepted().body(updatedCourse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id){
        var result = this.courseUseCase.delete(id);

        return ResponseEntity.ok().body("Deleted course");
    }

    @PatchMapping("/update_status/{id}")
    public ResponseEntity<Object> updateStatus(@PathVariable UUID id, @RequestBody Map<String, Object> data){
        try {
            CourseEntity updatedCourse = this.courseUseCase.updateStatus(id, data);
            return ResponseEntity.accepted().body(updatedCourse);
        } catch (Exception e) {
            return ResponseEntity.ofNullable("Course not updated");
        }
    }
}
