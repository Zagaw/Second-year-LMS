package com.example.lms.service;

import com.example.lms.dto.QuizSubmissionRequest;
import com.example.lms.dto.QuizSubmissionResponse;
import com.example.lms.entity.*;
import com.example.lms.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class QuizSubmissionService {

    private final QuizRepository quizRepo;
    private final QuizQuestionRepository questionRepo;
    private final UserRepository userRepo;
    private final StudentQuizSubmissionRepository submissionRepo;
    private final StudentQuizAnswerRepository answerRepo;

    public QuizSubmissionService(QuizRepository quizRepo,
                                 QuizQuestionRepository questionRepo,
                                 UserRepository userRepo,
                                 StudentQuizSubmissionRepository submissionRepo,
                                 StudentQuizAnswerRepository answerRepo) {
        this.quizRepo = quizRepo;
        this.questionRepo = questionRepo;
        this.userRepo = userRepo;
        this.submissionRepo = submissionRepo;
        this.answerRepo = answerRepo;
    }

    public QuizSubmissionResponse submitQuiz(Long quizId, QuizSubmissionRequest req) {
        Quiz quiz = quizRepo.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));
        User student = userRepo.findById(req.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<StudentQuizAnswer> savedAnswers = new ArrayList<>();
        int score = 0;

        // Create submission
        StudentQuizSubmission submission = StudentQuizSubmission.builder()
                .quiz(quiz)
                .student(student)
                .submittedAt(LocalDateTime.now())
                .build();
        submission = submissionRepo.save(submission);

        // Process answers
        for (QuizSubmissionRequest.AnswerDTO dto : req.getAnswers()) {
            QuizQuestion question = questionRepo.findById(dto.getQuestionId())
                    .orElseThrow(() -> new RuntimeException("Question not found"));
            boolean isCorrect = question.getCorrectAnswer().equalsIgnoreCase(dto.getSelectedAnswer());

            if (isCorrect) score++;

            StudentQuizAnswer ans = StudentQuizAnswer.builder()
                    .submission(submission)
                    .question(question)
                    .selectedAnswer(dto.getSelectedAnswer().toUpperCase())
                    .correct(isCorrect)
                    .build();

            savedAnswers.add(answerRepo.save(ans));
        }

        submission.setAnswers(savedAnswers);
        submission.setScore(score);
        submission.setTotalQuestions(savedAnswers.size());
        submissionRepo.save(submission);

        return QuizSubmissionResponse.builder()
                .submissionId(submission.getSubmissionId())
                .score(score)
                .totalQuestions(savedAnswers.size())
                .build();
    }

    public List<StudentQuizSubmission> getSubmissionsByStudent(Long quizId, Long studentId) {
        return submissionRepo.findByQuiz_Id(quizId).stream()
                .filter(sub -> sub.getStudent().getId().equals(studentId))
                .toList();
    }

    public List<StudentQuizSubmission> getSubmissionsForQuiz(Long quizId) {
        return submissionRepo.findByQuiz_Id(quizId);
    }

    public StudentQuizSubmission getSubmissionDetail(Long submissionId) {
        return submissionRepo.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission not found"));
    }

}
