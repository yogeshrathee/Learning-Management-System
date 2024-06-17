package com.example.learningmanagement.service;

import com.example.learningmanagement.entity.Exam;
import com.example.learningmanagement.entity.Student;
import com.example.learningmanagement.exception.ResourceNotFoundException;
import com.example.learningmanagement.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public Exam getExamById(Long examId) {
        return examRepository.findById(examId)
                .orElseThrow(() -> new ResourceNotFoundException("Exam", "id", examId));
    }

    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    public Exam updateExam(Long examId, Exam examDetails) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new ResourceNotFoundException("Exam", "id", examId));

        exam.setSubject(examDetails.getSubject());
        exam.setEnrolledStudents(examDetails.getEnrolledStudents());

        return examRepository.save(exam);
    }

    public void deleteExam(Long examId) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new ResourceNotFoundException("Exam", "id", examId));
        examRepository.delete(exam);
    }

    public Exam registerStudentForExam(Long examId, Student student) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new ResourceNotFoundException("Exam", "id", examId));

        List<Student> enrolledStudents = exam.getEnrolledStudents();
        enrolledStudents.add(student);
        exam.setEnrolledStudents(enrolledStudents);

        examRepository.save(exam);
        return exam;
    }
}
