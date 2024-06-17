package com.example.learningmanagement.service;

import com.example.learningmanagement.entity.Student;
import com.example.learningmanagement.entity.Subject;
import com.example.learningmanagement.repository.StudentRepository;
import com.example.learningmanagement.repository.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class StudentServiceTests {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllStudents() {
        when(studentRepository.findAll()).thenReturn(Collections.emptyList());

        Iterable<Student> students = studentService.getAllStudents();

        assertNotNull(students);
        assertEquals(0, ((Collection<?>) students).size());

        verify(studentRepository, times(1)).findAll();
        verifyNoMoreInteractions(studentRepository);
    }

    @Test
    public void testGetStudentById() {
        Student student = new Student();
        student.setId(1L);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Optional<Student> found = Optional.ofNullable(studentService.getStudentById(1L));

        assertTrue(found.isPresent());
        assertEquals(1L, found.get().getId());

        verify(studentRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(studentRepository);
    }

    @Test
    public void testCreateStudent() {
        Student student = new Student();
        student.setId(1L);
        student.setStudentName("John Doe");

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student createdStudent = studentService.createStudent(student);

        assertNotNull(createdStudent);
        assertEquals(1L, createdStudent.getId());
        assertEquals("John Doe", createdStudent.getStudentName());

        verify(studentRepository, times(1)).save(any(Student.class));
        verifyNoMoreInteractions(studentRepository);
    }

    // Add more tests as per your business logic and requirements
}
