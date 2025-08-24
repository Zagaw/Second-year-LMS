package com.example.lms.controller;

import com.example.lms.dto.*;
import com.example.lms.entity.StudentQuizSubmission;
import com.example.lms.service.QuizSubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuizSubmissionController {

    private final QuizSubmissionService submissionService;

    public QuizSubmissionController(QuizSubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping("/quizzes/{quizId}/submissions")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<QuizSubmissionResponse> submitQuiz(
            @PathVariable Long quizId,
            @RequestBody QuizSubmissionRequest req
    ) {
        return ResponseEntity.ok(submissionService.submitQuiz(quizId, req));
    }
    /** Student: get their own submissions for a quiz */
    @GetMapping("/quizzes/{quizId}/submissions/student/{studentId}")
    @PreAuthorize("#studentId == authentication.principal.id or hasRole('TEACHER')")
    public ResponseEntity<List<StudentQuizSubmission>> getStudentSubmissions(
            @PathVariable Long quizId,
            @PathVariable Long studentId
    ) {
        return ResponseEntity.ok(submissionService.getSubmissionsByStudent(quizId, studentId));
    }

    /** Teacher: get all submissions for a quiz */
    @GetMapping("/quizzes/{quizId}/submissions")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<List<StudentQuizSubmission>> getAllSubmissionsForQuiz(
            @PathVariable Long quizId
    ) {
        return ResponseEntity.ok(submissionService.getSubmissionsForQuiz(quizId));
    }

    /** Teacher/Student: get details of one submission (includes answers) */
    @GetMapping("/quizzes/{quizId}/submissions/{submissionId}")
    @PreAuthorize("hasAnyRole('TEACHER','STUDENT')")
    public ResponseEntity<StudentQuizSubmission> getSubmissionDetail(
            @PathVariable Long submissionId
    ) {
        return ResponseEntity.ok(submissionService.getSubmissionDetail(submissionId));
    }

    /** Get all submissions of a student across all quizzes */
    /*@GetMapping("/students/{studentId}/submissions")
    @PreAuthorize("#studentId == authentication.principal.id or hasRole('TEACHER')")
    public ResponseEntity<List<StudentQuizSubmission>> getAllSubmissionsByStudent(
            @PathVariable Long studentId
    ) {
        return ResponseEntity.ok(submissionService.getAllSubmissionsByStudent(studentId));
    }*/

    @GetMapping("/students/{studentId}/submissions")
    @PreAuthorize("#studentId == authentication.principal.id or hasRole('TEACHER')")
    public ResponseEntity<List<StudentSubmissionDTO>> getAllSubmissionsByStudent(
            @PathVariable Long studentId
    ) {
        List<StudentSubmissionDTO> submissions = submissionService.getAllSubmissionsByStudentDTO(studentId);
        return ResponseEntity.ok(submissions);
    }

    @GetMapping("/teacher/submissions")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<List<TeacherSubmissionDTO>> getAllSubmissionsForTeachers() {
        return ResponseEntity.ok(submissionService.getAllSubmissionsForTeachers());
    }

}


