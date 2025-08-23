package com.example.lms.controller;

import com.example.lms.dto.QuizSubmissionRequest;
import com.example.lms.dto.QuizSubmissionResponse;
import com.example.lms.entity.StudentQuizSubmission;
import com.example.lms.service.QuizSubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes/{quizId}/submissions")
public class QuizSubmissionController {

    private final QuizSubmissionService submissionService;

    public QuizSubmissionController(QuizSubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<QuizSubmissionResponse> submitQuiz(
            @PathVariable Long quizId,
            @RequestBody QuizSubmissionRequest req
    ) {
        return ResponseEntity.ok(submissionService.submitQuiz(quizId, req));
    }
    /** Student: get their own submissions for a quiz */
    @GetMapping("/student/{studentId}")
    @PreAuthorize("#studentId == authentication.principal.id or hasRole('TEACHER')")
    public ResponseEntity<List<StudentQuizSubmission>> getStudentSubmissions(
            @PathVariable Long quizId,
            @PathVariable Long studentId
    ) {
        return ResponseEntity.ok(submissionService.getSubmissionsByStudent(quizId, studentId));
    }

    /** Teacher: get all submissions for a quiz */
    @GetMapping
    @PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<List<StudentQuizSubmission>> getAllSubmissionsForQuiz(
            @PathVariable Long quizId
    ) {
        return ResponseEntity.ok(submissionService.getSubmissionsForQuiz(quizId));
    }

    /** Teacher/Student: get details of one submission (includes answers) */
    @GetMapping("/{submissionId}")
    @PreAuthorize("hasAuthority('TEACHER','STUDENT')")
    public ResponseEntity<StudentQuizSubmission> getSubmissionDetail(
            @PathVariable Long submissionId
    ) {
        return ResponseEntity.ok(submissionService.getSubmissionDetail(submissionId));
    }
}


