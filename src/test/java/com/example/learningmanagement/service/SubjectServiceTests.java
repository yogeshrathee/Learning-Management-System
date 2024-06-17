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

public class SubjectServiceTests {

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private SubjectService subjectService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllSubjects() {
        when(subjectRepository.findAll()).thenReturn(Collections.emptyList());

        Iterable<Subject> subjects = subjectService.getAllSubjects();

        assertNotNull(subjects);
        assertEquals(0, ((Collection<?>) subjects).size());

        verify(subjectRepository, times(1)).findAll();
        verifyNoMoreInteractions(subjectRepository);
    }

    @Test
    public void testGetSubjectById() {
        Subject subject = new Subject();
        subject.setId(1L);
        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));

        Optional<Subject> found = Optional.ofNullable(subjectService.getSubjectById(1L));

        assertTrue(found.isPresent());
        assertEquals(1L, found.get().getId());

        verify(subjectRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(subjectRepository);
    }

    @Test
    public void testCreateSubject() {
        Subject subject = new Subject();
        subject.setId(1L);
        subject.setSubjectName("Math");

        when(subjectRepository.save(any(Subject.class))).thenReturn(subject);

        Subject createdSubject = subjectService.createSubject(subject);

        assertNotNull(createdSubject);
        assertEquals(1L, createdSubject.getId());
        assertEquals("Math", createdSubject.getSubjectName());

        verify(subjectRepository, times(1)).save(any(Subject.class));
        verifyNoMoreInteractions(subjectRepository);
    }

    // Add more tests as per your business logic and requirements
}
