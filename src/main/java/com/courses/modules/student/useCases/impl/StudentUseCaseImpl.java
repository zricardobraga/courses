package com.courses.modules.student.useCases.impl;

import com.courses.modules.student.entity.StudentEntity;
import com.courses.modules.student.repository.StudentRepository;
import com.courses.modules.student.useCases.StudentUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentUseCaseImpl implements StudentUseCase {

    private StudentRepository studentRepository;

    //injeção de dependência via construtor
    public StudentUseCaseImpl(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    @Override
    public StudentEntity register(StudentEntity studentEntity) {
        this.studentRepository.findByEmail(studentEntity.getEmail()).ifPresent((student) -> {
            throw new NoSuchElementException("This e-mail already exists.");
        });

        return this.studentRepository.save(studentEntity);
    }

    @Override
    public List<StudentEntity> listAll() {
        return this.studentRepository.findAll();
    }
}
