package com.example.lms.service;

import com.example.lms.entity.StudentQuizSubmission;
import com.example.lms.repository.StudentQuizSubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentQuizSubmissionService {
    private final StudentQuizSubmissionRepository submissionRepository;

    @Autowired
    public StudentQuizSubmissionService(StudentQuizSubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    public List<StudentQuizSubmission> getAllSubmissions() {
        return submissionRepository.findAll();
    }

    public Optional<StudentQuizSubmission> getSubmissionById(Long id) {
        return submissionRepository.findById(id);
    }

    public StudentQuizSubmission saveSubmission(StudentQuizSubmission submission) {
        return submissionRepository.save(submission);
    }

    public void deleteSubmission(Long id) {
        submissionRepository.deleteById(id);
    }
}
