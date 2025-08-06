package com.example.lms.courseController;

import com.example.lms.course.Course;
import com.example.lms.courseRepository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable("id") String courseCode) {
        return courseRepository.findById(courseCode)
                .orElseThrow(() -> new RuntimeException("Course not found with code: " + courseCode));
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable("id") String courseCode, @RequestBody Course updatedCourse) {
        Course course = courseRepository.findById(courseCode)
                .orElseThrow(() -> new RuntimeException("Course not found with code: " + courseCode));

        course.setName(updatedCourse.getName());
        course.setDescription(updatedCourse.getDescription());

        return courseRepository.save(course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable("id") String courseCode) {
        courseRepository.deleteById(courseCode);
    }
}