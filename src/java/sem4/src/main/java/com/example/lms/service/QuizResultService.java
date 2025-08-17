package com.example.lms.service;

import com.example.lms.entity.QuizResult;
import com.example.lms.repository.QuizResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizResultService {
    private final QuizResultRepository quizResultRepository;

    @Autowired
    public QuizResultService(QuizResultRepository quizResultRepository) {
        this.quizResultRepository = quizResultRepository;
    }

    public List<QuizResult> getAllQuizResults() {
        return quizResultRepository.findAll();
    }

    public Optional<QuizResult> getQuizResultById(Long id) {
        return quizResultRepository.findById(id);
    }

    public QuizResult saveQuizResult(QuizResult quizResult) {
        return quizResultRepository.save(quizResult);
    }

    public void deleteQuizResult(Long id) {
        quizResultRepository.deleteById(id);
    }
}
