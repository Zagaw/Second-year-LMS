// service/QuizService.java
package com.example.lms.service;

import com.example.lms.dto.*;
import com.example.lms.entity.Material;
import com.example.lms.entity.Quiz;
import com.example.lms.entity.QuizQuestion;
import com.example.lms.repository.MaterialRepository;
import com.example.lms.repository.QuizQuestionRepository;
import com.example.lms.repository.QuizRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizQuestionRepository questionRepository;
    private final MaterialRepository materialRepository;

    public QuizService(QuizRepository quizRepository,
                       QuizQuestionRepository questionRepository,
                       MaterialRepository materialRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.materialRepository = materialRepository;
    }

    public Quiz createQuiz(Long materialId, CreateQuizRequest req) {
        Material material = materialRepository.findById(materialId)
                .orElseThrow(() -> new RuntimeException("Material not found"));
        Quiz quiz = Quiz.builder()
                .title(req.getTitle())
                .material(material)
                .build();
        return quizRepository.save(quiz);
    }

    public QuizQuestion addQuestion(Long quizId, CreateQuestionRequest req) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));
        validateAnswerLetter(req.getCorrectAnswer());

        QuizQuestion q = QuizQuestion.builder()
                .quiz(quiz)
                .questionText(req.getQuestionText())
                .optionA(req.getOptionA())
                .optionB(req.getOptionB())
                .optionC(req.getOptionC())
                .optionD(req.getOptionD())
                .correctAnswer(req.getCorrectAnswer().toUpperCase())
                .build();
        return questionRepository.save(q);
    }

    public List<QuizQuestion> addQuestionsBulk(Long quizId, BulkQuestionsRequest bulk) {
        List<QuizQuestion> saved = new ArrayList<>();
        for (CreateQuestionRequest q : bulk.getQuestions()) {
            saved.add(addQuestion(quizId, q));
        }
        return saved;
    }

    public List<Quiz> getQuizzesByMaterial(Long materialId) {
        return quizRepository.findByMaterial_MaterialId(materialId);
    }

    public List<QuizQuestion> getQuestions(Long quizId) {
        ensureQuizExists(quizId);
        return questionRepository.findByQuiz_Id(quizId);
    }

    public void deleteQuiz(Long quizId) {
        if (!quizRepository.existsById(quizId)) throw new RuntimeException("Quiz not found");
        quizRepository.deleteById(quizId);
    }

    public void deleteQuestion(Long questionId) {
        if (!questionRepository.existsById(questionId)) throw new RuntimeException("Question not found");
        questionRepository.deleteById(questionId);
    }

    private void ensureQuizExists(Long quizId) {
        if (!quizRepository.existsById(quizId)) throw new RuntimeException("Quiz not found");
    }

    private void validateAnswerLetter(String letter) {
        String L = letter == null ? "" : letter.toUpperCase();
        if (!L.equals("A") && !L.equals("B") && !L.equals("C") && !L.equals("D")) {
            throw new RuntimeException("correctAnswer must be one of A, B, C, D");
        }
    }
}

