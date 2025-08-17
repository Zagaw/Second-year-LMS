package com.example.lms.repository;

import com.example.lms.entity.StudentQuizSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentQuizSubmissionRepository extends JpaRepository<StudentQuizSubmission, Long> {
}
