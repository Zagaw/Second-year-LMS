package com.example.lms.service;

import com.example.lms.entity.Quiz;
import com.example.lms.entity.QuizQuestion;
import com.example.lms.repository.QuizQuestionRepository;
import com.example.lms.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizQuestionService {

    private final QuizQuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    public QuizQuestionService(QuizQuestionRepository questionRepository, QuizRepository quizRepository) {
        this.questionRepository = questionRepository;
        this.quizRepository = quizRepository;
    }

    public List<QuizQuestion> getAllQuestionsByQuizId(Long quizId) {
        return questionRepository.findByQuizId(quizId);
    }

    public QuizQuestion addQuestionToQuiz(Long quizId, QuizQuestion question) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found with ID: " + quizId));
        question.setQuiz(quiz);
        return questionRepository.save(question);
    }

    public QuizQuestion getQuestion(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found with ID: " + questionId));
    }

    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }
}
