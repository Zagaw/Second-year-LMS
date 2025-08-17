package com.example.lms.controller;

import com.example.lms.entity.QuizQuestion;
import com.example.lms.service.QuizQuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz-questions")
public class QuizQuestionController {

    private final QuizQuestionService questionService;

    public QuizQuestionController(QuizQuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/quiz/{quizId}")
    public List<QuizQuestion> getAllQuestionsForQuiz(@PathVariable Long quizId) {
        return questionService.getAllQuestionsByQuizId(quizId);
    }

    @PostMapping("/quiz/{quizId}")
    public QuizQuestion addQuestion(@PathVariable Long quizId, @RequestBody QuizQuestion question) {
        return questionService.addQuestionToQuiz(quizId, question);
    }

    @GetMapping("/{id}")
    public QuizQuestion getQuestionById(@PathVariable Long id) {
        return questionService.getQuestion(id);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
    }
}
