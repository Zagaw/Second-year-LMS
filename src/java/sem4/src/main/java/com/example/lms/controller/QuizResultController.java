package com.example.lms.controller;

import com.example.lms.entity.QuizResult;
import com.example.lms.repository.QuizResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz-results")
public class QuizResultController {

    @Autowired
    private QuizResultRepository quizResultRepository;

    @GetMapping
    public List<QuizResult> getAllResults() {
        return quizResultRepository.findAll();
    }

    @GetMapping("/{id}")
    public QuizResult getResultById(@PathVariable Long id) {
        return quizResultRepository.findById(id).orElse(null);
    }

    @PostMapping
    public QuizResult createResult(@RequestBody QuizResult quizResult) {
        return quizResultRepository.save(quizResult);
    }

    @PutMapping("/{id}")
    public QuizResult updateResult(@PathVariable Long id, @RequestBody QuizResult updatedResult) {
        return quizResultRepository.findById(id).map(res -> {
            // Set fields to update as needed
            return quizResultRepository.save(res);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteResult(@PathVariable Long id) {
        quizResultRepository.deleteById(id);
    }
}
