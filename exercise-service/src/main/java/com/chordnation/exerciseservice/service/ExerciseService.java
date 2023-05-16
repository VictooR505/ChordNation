package com.chordnation.exerciseservice.service;

import com.chordnation.exerciseservice.domain.dto.ExerciseDTO;
import com.chordnation.exerciseservice.mapper.ExerciseMapper;
import com.chordnation.exerciseservice.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapper exerciseMapper;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseMapper = new ExerciseMapper();
    }

    public List<ExerciseDTO> getAllExercises(){
        return exerciseRepository.findAll().stream().map(exerciseMapper::toDTO).toList();
    }

    public void addExercise(ExerciseDTO exerciseDTO){
        exerciseRepository.save(exerciseMapper.toEntity(exerciseDTO));
    }

    public void deleteExercise(Long id){
        exerciseRepository.deleteById(id);
    }
}
