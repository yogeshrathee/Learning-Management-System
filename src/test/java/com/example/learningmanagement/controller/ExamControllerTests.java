package com.example.learningmanagement.controller;

import com.example.learningmanagement.entity.Exam;
import com.example.learningmanagement.entity.Student;
import com.example.learningmanagement.entity.Subject;
import com.example.learningmanagement.exception.GlobalExceptionHandler;
import com.example.learningmanagement.exception.ResourceNotFoundException;
import com.example.learningmanagement.service.ExamService;
import com.example.learningmanagement.service.StudentService;
import com.example.learningmanagement.service.SubjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ExamControllerTests {

    private MockMvc mockMvc;

    @Mock
    private ExamService examService;

    @InjectMocks
    private ExamController examController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(examController)
                                 .setControllerAdvice(new GlobalExceptionHandler())
                                 .build();
    }

    @Test
    public void testGetAllExams() throws Exception {
        when(examService.getAllExams()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/exams")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());

        verify(examService, times(1)).getAllExams();
        verifyNoMoreInteractions(examService);
    }

    @Test
    public void testGetExamById() throws Exception {
        Exam exam = new Exam();
        exam.setId(1L);
        exam.setSubject(new Subject());
        exam.setEnrolledStudents(Collections.emptyList());

        when(examService.getExamById(1L)).thenReturn(exam);

        mockMvc.perform(get("/exams/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));

        verify(examService, times(1)).getExamById(1L);
        verifyNoMoreInteractions(examService);
    }

    @Test
    public void testCreateExam() throws Exception {
        Exam exam = new Exam();
        exam.setId(1L);
        exam.setSubject(new Subject());
        exam.setEnrolledStudents(Collections.emptyList());

        when(examService.createExam(any(Exam.class))).thenReturn(exam);

        mockMvc.perform(post("/exams")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"subject\": { \"id\": 1 }, \"enrolledStudents\": [] }"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));

        verify(examService, times(1)).createExam(any(Exam.class));
        verifyNoMoreInteractions(examService);
    }

    @Test
    public void testUpdateExam() throws Exception {
        Exam exam = new Exam();
        exam.setId(1L);
        exam.setSubject(new Subject());
        exam.setEnrolledStudents(Collections.emptyList());

        when(examService.updateExam(eq(1L), any(Exam.class))).thenReturn(exam);

        mockMvc.perform(put("/exams/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"subject\": { \"id\": 1 }, \"enrolledStudents\": [] }"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));

        verify(examService, times(1)).updateExam(eq(1L), any(Exam.class));
        verifyNoMoreInteractions(examService);
    }

    @Test
    public void testDeleteExam() throws Exception {
        mockMvc.perform(delete("/exams/1"))
                .andExpect(status().isOk());

        verify(examService, times(1)).deleteExam(1L);
        verifyNoMoreInteractions(examService);
    }

    @Test
    public void testRegisterForExam() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setStudentName("John Doe");

        doNothing().when(examService).registerStudentForExam(eq(1L), any(Student.class));

        mockMvc.perform(post("/exams/1/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\": 1, \"studentName\": \"John Doe\" }"))
                .andExpect(status().isOk());

        verify(examService, times(1)).registerStudentForExam(eq(1L), any(Student.class));
        verifyNoMoreInteractions(examService);
    }

    @Test
    public void testRegisterForExam_NotFound() throws Exception {
        doThrow(new ResourceNotFoundException("Exam", "id", 1L))
                .when(examService).registerStudentForExam(eq(1L), any(Student.class));

        mockMvc.perform(post("/exams/1/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\": 1, \"studentName\": \"John Doe\" }"))
                .andExpect(status().isNotFound());

        verify(examService, times(1)).registerStudentForExam(eq(1L), any(Student.class));
        verifyNoMoreInteractions(examService);
    }
}
