package com.chordnation.chordnation.exercise;

import com.chordnation.chordnation.enums.KeyWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findAllBySection(Section section);

  //  @Query("SELECT e FROM Exercise e WHERE e.requiredPoints<:points")
    List<Exercise> findByRequiredPointsLessThanEqual(int points);
}
