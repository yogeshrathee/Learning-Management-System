package com.example.learningmanagement.controller;

import com.example.learningmanagement.entity.Exam;
import com.example.learningmanagement.entity.Student;
import com.example.learningmanagement.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @GetMapping
    public List<Exam> getAllExams() {
        return examService.getAllExams();
    }

    @GetMapping("/{examId}")
    public ResponseEntity<Exam> getExamById(@PathVariable Long examId) {
        Exam exam = examService.getExamById(examId);
        return ResponseEntity.ok().body(exam);
    }

    @PostMapping
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam) {
        Exam createdExam = examService.createExam(exam);
        return new ResponseEntity<>(createdExam, HttpStatus.CREATED);
    }

    @PutMapping("/{examId}")
    public ResponseEntity<Exam> updateExam(@PathVariable Long examId, @RequestBody Exam examDetails) {
        Exam updatedExam = examService.updateExam(examId, examDetails);
        return ResponseEntity.ok().body(updatedExam);
    }

    @DeleteMapping("/{examId}")
    public ResponseEntity<?> deleteExam(@PathVariable Long examId) {
        examService.deleteExam(examId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{examId}/register")
    public ResponseEntity<?> registerForExam(@PathVariable Long examId, @RequestBody Student student) {
        examService.registerStudentForExam(examId, student);
        return ResponseEntity.ok().build();
    }
}
