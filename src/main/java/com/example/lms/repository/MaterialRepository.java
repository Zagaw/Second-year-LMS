package com.example.lms.repository;

import com.example.lms.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findByCourse_CourseId(Long courseId);
}