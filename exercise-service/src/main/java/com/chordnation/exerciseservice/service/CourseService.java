package com.chordnation.exerciseservice.service;

import com.chordnation.exerciseservice.domain.dto.CourseDTO;
import com.chordnation.exerciseservice.mapper.CourseMapper;
import com.chordnation.exerciseservice.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
        this.courseMapper = new CourseMapper();
    }

    public List<CourseDTO> getAllCourses(){
        return courseRepository.findAll().stream().map(courseMapper::toDTO).toList();
    }

    public void addCourse(CourseDTO courseDTO){
        courseRepository.save(courseMapper.toEntity(courseDTO));
    }

    public void deleteCourse(Long id){
        courseRepository.deleteById(id);
    }
}
