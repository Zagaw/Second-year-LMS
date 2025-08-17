package com.example.lms.controller;

import com.example.lms.entity.StudentQuizSubmission;
import com.example.lms.repository.StudentQuizSubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student-quiz-submissions")
public class StudentQuizSubmissionController {

    @Autowired
    private StudentQuizSubmissionRepository repository;

    @GetMapping
    public List<StudentQuizSubmission> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public StudentQuizSubmission getById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public StudentQuizSubmission create(@RequestBody StudentQuizSubmission submission) {
        return repository.save(submission);
    }

    @PutMapping("/{id}")
    public StudentQuizSubmission update(@PathVariable Long id, @RequestBody StudentQuizSubmission submission) {
        return repository.findById(id)
                .map(existing -> {
                    // update fields as necessary
                    return repository.save(existing);
                }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}