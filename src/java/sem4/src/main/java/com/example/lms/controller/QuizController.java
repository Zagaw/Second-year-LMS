package com.example.lms.controller;

import com.example.lms.entity.Quiz;
import com.example.lms.service.QuizService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @GetMapping("/{id}")
    public Quiz getQuizById(@PathVariable Long id) {
        return quizService.getQuizById(id);
    }

    @PostMapping("/material/{materialId}")
    public Quiz createQuiz(@PathVariable Long materialId, @RequestBody Quiz quiz) {
        return quizService.createQuiz(materialId, quiz);
    }

    @PutMapping("/{quizId}")
    public Quiz updateQuiz(@PathVariable Long quizId, @RequestBody Quiz quiz) {
        return quizService.updateQuiz(quizId, quiz);
    }
}
