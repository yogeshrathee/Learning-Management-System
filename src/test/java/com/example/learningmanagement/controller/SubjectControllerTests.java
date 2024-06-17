package com.example.learningmanagement.controller;

import com.example.learningmanagement.entity.Subject;
import com.example.learningmanagement.exception.GlobalExceptionHandler;
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

public class SubjectControllerTests {

    private MockMvc mockMvc;

    @Mock
    private SubjectService subjectService;

    @InjectMocks
    private SubjectController subjectController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(subjectController)
                                 .setControllerAdvice(new GlobalExceptionHandler())
                                 .build();
    }

    @Test
    public void testGetAllSubjects() throws Exception {
        when(subjectService.getAllSubjects()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/subjects")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());

        verify(subjectService, times(1)).getAllSubjects();
        verifyNoMoreInteractions(subjectService);
    }

    @Test
    public void testGetSubjectById() throws Exception {
        Subject subject = new Subject();
        subject.setId(1L);
        subject.setSubjectName("Math");

        when(subjectService.getSubjectById(1L)).thenReturn(subject);

        mockMvc.perform(get("/subjects/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.subjectName").value("Math"));

        verify(subjectService, times(1)).getSubjectById(1L);
        verifyNoMoreInteractions(subjectService);
    }

    @Test
    public void testCreateSubject() throws Exception {
        Subject subject = new Subject();
        subject.setId(1L);
        subject.setSubjectName("Science");

        when(subjectService.createSubject(any(Subject.class))).thenReturn(subject);

        mockMvc.perform(post("/subjects")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"subjectName\": \"Science\" }"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.subjectName").value("Science"));

        verify(subjectService, times(1)).createSubject(any(Subject.class));
        verifyNoMoreInteractions(subjectService);
    }

    @Test
    public void testUpdateSubject() throws Exception {
        Subject subject = new Subject();
        subject.setId(1L);
        subject.setSubjectName("Science");

        when(subjectService.updateSubject(eq(1L), any(Subject.class))).thenReturn(subject);

        mockMvc.perform(put("/subjects/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"subjectName\": \"Science\" }"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.subjectName").value("Science"));

        verify(subjectService, times(1)).updateSubject(eq(1L), any(Subject.class));
        verifyNoMoreInteractions(subjectService);
    }

    @Test
    public void testDeleteSubject() throws Exception {
        mockMvc.perform(delete("/subjects/1"))
                .andExpect(status().isOk());

        verify(subjectService, times(1)).deleteSubject(1L);
        verifyNoMoreInteractions(subjectService);
    }
}
