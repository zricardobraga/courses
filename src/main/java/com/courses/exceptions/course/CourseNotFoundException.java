package com.courses.exceptions.course;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException() {
        super("Course not found exception");
    }
}
