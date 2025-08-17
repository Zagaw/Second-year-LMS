package com.example.lms.controller;

import com.example.lms.entity.StudentQuizAnswer;
import com.example.lms.repository.StudentQuizAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student-quiz-answers")
public class StudentQuizAnswerController {

    @Autowired
    private StudentQuizAnswerRepository repository;

    @GetMapping
    public List<StudentQuizAnswer> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public StudentQuizAnswer getById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public StudentQuizAnswer create(@RequestBody StudentQuizAnswer answer) {
        return repository.save(answer);
    }

    @PutMapping("/{id}")
    public StudentQuizAnswer update(@PathVariable Long id, @RequestBody StudentQuizAnswer answer) {
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
