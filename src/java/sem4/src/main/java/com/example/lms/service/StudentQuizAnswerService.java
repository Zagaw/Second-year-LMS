package com.example.lms.service;

import com.example.lms.entity.StudentQuizAnswer;
import com.example.lms.repository.StudentQuizAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentQuizAnswerService {
    private final StudentQuizAnswerRepository answerRepository;

    @Autowired
    public StudentQuizAnswerService(StudentQuizAnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public List<StudentQuizAnswer> getAllAnswers() {
        return answerRepository.findAll();
    }

    public Optional<StudentQuizAnswer> getAnswerById(Long id) {
        return answerRepository.findById(id);
    }

    public StudentQuizAnswer saveAnswer(StudentQuizAnswer answer) {
        return answerRepository.save(answer);
    }

    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }
}
