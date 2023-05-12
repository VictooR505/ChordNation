package com.chordnation.exerciseservice.service;

import com.chordnation.exerciseservice.domain.Course;
import com.chordnation.exerciseservice.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public void addCourse(Course course){
        courseRepository.save(course);
    }

    public void deleteCourse(Long id){
        courseRepository.deleteById(id);
    }
}
