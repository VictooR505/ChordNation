package com.chordnation.exerciseservice.mapper;

import com.chordnation.exerciseservice.domain.Course;
import com.chordnation.exerciseservice.domain.dto.CourseDTO;

public class CourseMapper {

    public CourseMapper() {
    }

    public CourseDTO toDTO(Course course){
        CourseDTO courseDTO = new CourseDTO(
                course.getId(),
                course.getLevel(),
                course.getDescription(),
                course.getLink()
        );
        return courseDTO;
    }

    public Course toEntity(CourseDTO courseDTO){
        Course course = new Course(
                courseDTO.id(),
                courseDTO.level(),
                courseDTO.description(),
                courseDTO.link()
        );
        return course;
    }
}
