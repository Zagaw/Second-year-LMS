package com.example.lms.controller;

import com.example.lms.entity.Course;
import com.example.lms.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping("/{courseId}/assign/{userId}")
    public Course assignCourseToUser(@PathVariable Long courseId, @PathVariable Long userId) {
        return courseService.assignCourseToUser(courseId, userId);
    }
}
