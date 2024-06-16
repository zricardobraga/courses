package com.courses.modules.course.UseCases.impl;

import com.courses.exceptions.course.CourseNotFoundException;
import com.courses.modules.ENUM.StatusEnum;
import com.courses.modules.course.UseCases.CourseUseCase;
import com.courses.modules.course.entity.CourseEntity;
import com.courses.modules.course.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CourseUseCaseImpl implements CourseUseCase {

    private final CourseRepository courseRepository;

    public CourseUseCaseImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseEntity register(CourseEntity courseEntity) {
        this.courseRepository.findByName(courseEntity.getName()).ifPresent(
                (course) -> {
                    throw new CourseNotFoundException();
                }
        );

        return this.courseRepository.save(courseEntity);
    }

    @Override
    public List<CourseEntity> listAll() {
        return this.courseRepository.findAll();
    }

    @Override
    public CourseEntity update(UUID id, Map<String, Object> data) {

        CourseEntity courseToBeUpdated = this.courseRepository.findById(id).orElseThrow(
                () -> new CourseNotFoundException()
        );

        data.forEach((key, value) -> {
            if (key.equals("name")) {
                courseToBeUpdated.setName((String) value);
            } else if (key.equals("category")) {
                courseToBeUpdated.setCategory((String) value);
            }
        });

        return this.courseRepository.save(courseToBeUpdated);
    }

    @Override
    public String delete(UUID id) {
        CourseEntity courseToDeleted = this.courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException());
        if (courseToDeleted != null) {
            this.courseRepository.deleteById(id);
        }
        return "Deleted course";
    }

    @Override
    public CourseEntity updateStatus(UUID id, Map<String, Object> data) {
        CourseEntity courseToBeStatusUpdated = this.courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException());

        if (data.containsKey("status")) {
            //pega o status do map e faz converte (cast) para uma string
            var statusReceived = (String) data.get("status");
            //converte o status em enum
            StatusEnum statusEnum = StatusEnum.valueOf(statusReceived.toUpperCase());
            //altera o status
            courseToBeStatusUpdated.setStatus(statusEnum);
        }
        return this.courseRepository.save(courseToBeStatusUpdated);
    }
}
