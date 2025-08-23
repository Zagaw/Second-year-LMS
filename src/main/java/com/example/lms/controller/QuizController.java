// controller/QuizController.java
package com.example.lms.controller;

import com.example.lms.dto.*;
import com.example.lms.entity.Quiz;
import com.example.lms.entity.QuizQuestion;
import com.example.lms.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService quizService;
    public QuizController(QuizService quizService) { this.quizService = quizService; }

    /** Create a quiz under a material */
    @PostMapping("/material/{materialId}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public Quiz createQuiz(@PathVariable Long materialId,
                           @Valid @RequestBody CreateQuizRequest req) {
        return quizService.createQuiz(materialId, req);
    }

    /** List quizzes for a material */
    @GetMapping("/material/{materialId}")
    public List<Quiz> getByMaterial(@PathVariable Long materialId) {
        return quizService.getQuizzesByMaterial(materialId);
    }

    /** Add a single question to a quiz */
    @PostMapping("/{quizId}/questions")
    @PreAuthorize("hasAuthority('TEACHER')")
    public QuizQuestion addQuestion(@PathVariable Long quizId,
                                    @Valid @RequestBody CreateQuestionRequest req) {
        return quizService.addQuestion(quizId, req);
    }

    /** Add multiple questions at once */
    @PostMapping("/{quizId}/questions/bulk")
    @PreAuthorize("hasAuthority('TEACHER')")
    public List<QuizQuestion> addQuestionsBulk(@PathVariable Long quizId,
                                               @Valid @RequestBody BulkQuestionsRequest bulk) {
        return quizService.addQuestionsBulk(quizId, bulk);
    }

    /** Get all questions for a quiz (do not expose correct answers to students on frontend) */
    @GetMapping("/{quizId}/questions")
    public List<QuizQuestion> getQuestions(@PathVariable Long quizId) {
        return quizService.getQuestions(quizId);
    }

    /** Delete a quiz (removes its questions due to orphanRemoval=true) */
    @DeleteMapping("/{quizId}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<String> deleteQuiz(@PathVariable Long quizId) {
        try {
            quizService.deleteQuiz(quizId);
            return ResponseEntity.ok("Quiz deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /** Delete a single question */
    @DeleteMapping("/questions/{questionId}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long questionId) {
        try {
            quizService.deleteQuestion(questionId);
            return ResponseEntity.ok("Question deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
