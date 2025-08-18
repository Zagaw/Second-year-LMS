// QuizRepository.java
package com.example.lms.repository;

import com.example.lms.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByMaterial_MaterialId(Long materialId);
}

