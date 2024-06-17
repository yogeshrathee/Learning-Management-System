package com.example.learningmanagement.service;

import com.example.learningmanagement.entity.Exam;
import com.example.learningmanagement.entity.Student;
import com.example.learningmanagement.entity.Subject;
import com.example.learningmanagement.repository.ExamRepository;
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

public class ExamServiceTests {

    @Mock
    private ExamRepository examRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private ExamService examService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllExams() {
        when(examRepository.findAll()).thenReturn(Collections.emptyList());

        Iterable<Exam> exams = examService.getAllExams();

        assertNotNull(exams);
        assertEquals(0, ((Collection<?>) exams).size());

        verify(examRepository, times(1)).findAll();
        verifyNoMoreInteractions(examRepository);
    }

    @Test
    public void testGetExamById() {
        Exam exam = new Exam();
        exam.setId(1L);
        when(examRepository.findById(1L)).thenReturn(Optional.of(exam));

        Optional<Exam> found = Optional.ofNullable(examService.getExamById(1L));

        assertTrue(found.isPresent());
        assertEquals(1L, found.get().getId());

        verify(examRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(examRepository);
    }

    @Test
    public void testRegisterStudentForExam() {
        Exam exam = new Exam();
        exam.setId(1L);
        Subject subject = new Subject();
        subject.setId(1L);
        exam.setSubject(subject);

        Student student = new Student();
        student.setId(1L);
        student.setStudentName("John Doe");

        when(examRepository.findById(1L)).thenReturn(Optional.of(exam));
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(examRepository.save(any(Exam.class))).thenReturn(exam);

        Exam updatedExam = examService.registerStudentForExam(1L, student);

        assertNotNull(updatedExam);
        assertEquals(1, updatedExam.getEnrolledStudents().size());

        verify(examRepository, times(1)).findById(1L);
        verify(examRepository, times(1)).save(any(Exam.class));
        verify(studentRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(examRepository);
        verifyNoMoreInteractions(studentRepository);
    }

    // Add more tests as per your business logic and requirements
}
