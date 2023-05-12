package com.chordnation.exerciseservice.controller;


import com.chordnation.exerciseservice.domain.Course;
import com.chordnation.exerciseservice.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @PostMapping
    public void addCourse(@RequestBody Course course){
        courseService.addCourse(course);
    }

    @DeleteMapping
    public void deleteCourse(@RequestBody Long id){
        courseService.deleteCourse(id);
    }
}
