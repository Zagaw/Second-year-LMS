package com.example.lms.service;

import com.example.lms.entity.Course;
import com.example.lms.entity.Material;
import com.example.lms.repository.CourseRepository;
import com.example.lms.repository.MaterialRepository;
import com.example.lms.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final CourseRepository courseRepository;
    private final QuizRepository quizRepository;

    public MaterialService(MaterialRepository materialRepository, CourseRepository courseRepository, QuizRepository quizRepository) {
        this.materialRepository = materialRepository;
        this.courseRepository = courseRepository;
        this.quizRepository = quizRepository;
    }

    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

    public List<Material> getMaterialByCourse(Long courseId) {
        return materialRepository.findByCourse_CourseId(courseId);
    }

    public Material createMaterial(Long courseId,Material material) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        material.setCourse(course);
        return materialRepository.save(material);
    }

    public void deleteMaterial(Long courseId, Long materialId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Material material = materialRepository.findById(materialId)
                .orElseThrow(() -> new RuntimeException("Material not found"));

        if (!material.getCourse().getCourseId().equals(course.getCourseId())) {
            throw new RuntimeException("Material does not belong to this course");
        }

        boolean hasQuiz = !quizRepository.findByMaterial_MaterialId(materialId).isEmpty();
        if (hasQuiz) {
            throw new RuntimeException("Cannot delete this material because quizzes are linked.");
        }

        materialRepository.delete(material);
    }
}
