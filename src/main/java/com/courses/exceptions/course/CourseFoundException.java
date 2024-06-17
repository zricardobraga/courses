package com.courses.exceptions.course;

public class CourseFoundException extends RuntimeException {
    public CourseFoundException() {
        super("Course previously registered in the system");
    }
}
