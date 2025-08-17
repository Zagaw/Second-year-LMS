package com.example.lms.service;

import com.example.lms.entity.Material;
import com.example.lms.entity.Quiz;
import com.example.lms.repository.MaterialRepository;
import com.example.lms.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final MaterialRepository materialRepository;

    public QuizService(QuizRepository quizRepository, MaterialRepository materialRepository) {
        this.quizRepository = quizRepository;
        this.materialRepository = materialRepository;
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found with ID: " + id));
    }

    public Quiz createQuiz(Long materialId, Quiz quiz) {
        Material material = materialRepository.findById(materialId)
                .orElseThrow(() -> new RuntimeException("Material not found with ID: " + materialId));
        quiz.setMaterial(material);
        return quizRepository.save(quiz);
    }

    public Quiz updateQuiz(Long quizId, Quiz quizDetails) {
        Quiz existingQuiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found with ID: " + quizId));

        existingQuiz.setTitle(quizDetails.getTitle());

        if (quizDetails.getMaterial() != null && quizDetails.getMaterial().getId() != null) {
            Long newMaterialId = quizDetails.getMaterial().getId();
            Material newMaterial = materialRepository.findById(newMaterialId)
                    .orElseThrow(() -> new RuntimeException("Material not found with ID: " + newMaterialId));
            existingQuiz.setMaterial(newMaterial);
        }

        return quizRepository.save(existingQuiz);
    }
}
