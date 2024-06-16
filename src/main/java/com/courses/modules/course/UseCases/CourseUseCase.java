package com.courses.modules.course.UseCases;

import com.courses.modules.course.entity.CourseEntity;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface CourseUseCase {
    CourseEntity register(CourseEntity courseEntity);
    List<CourseEntity> listAll();
    CourseEntity update(UUID id, Map<String, Object> data);
    String delete(UUID id);
    CourseEntity updateStatus(UUID id, Map<String, Object> data);
}
