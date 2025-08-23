// service/QuizService.java
package com.example.lms.service;

import com.example.lms.dto.*;
import com.example.lms.entity.Material;
import com.example.lms.entity.Quiz;
import com.example.lms.entity.QuizQuestion;
import com.example.lms.repository.*;
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
    private final StudentQuizAnswerRepository answerRepository;
    private final StudentQuizSubmissionRepository submissionRepository;

    public QuizService(QuizRepository quizRepository,
                       QuizQuestionRepository questionRepository,
                       MaterialRepository materialRepository,
                       StudentQuizAnswerRepository answerRepository,
                       StudentQuizSubmissionRepository submissionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.materialRepository = materialRepository;
        this.answerRepository = answerRepository;
        this.submissionRepository = submissionRepository;
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

        // 🚨 check if submissions exist before deleting quiz
        boolean hasSubmissions = !submissionRepository.findByQuiz_Id(quizId).isEmpty();
        if (hasSubmissions) {
            throw new RuntimeException("Cannot delete this quiz because students have already submitted answers.");
        }

        quizRepository.deleteById(quizId);
    }

    public void deleteQuestion(Long questionId) {
        if (!questionRepository.existsById(questionId)) throw new RuntimeException("Question not found");

        // 🚨 check if student answers exist
        boolean hasAnswers = answerRepository.existsByQuestion_QuestionId(questionId);
        if (hasAnswers) {
            throw new RuntimeException("Cannot delete this question because students have already submitted answers.");
        }
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

