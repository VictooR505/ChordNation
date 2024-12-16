package com.chordnation.chordnation.exercise;

import com.chordnation.chordnation.enums.Level;
import com.chordnation.chordnation.user.ExercisesDone;
import com.chordnation.chordnation.user.User;
import com.chordnation.chordnation.user.UserDetails;
import com.chordnation.chordnation.user.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final SectionRepository sectionRepository;
    private final UserRepository userRepository;

    public ExerciseService(ExerciseRepository exerciseRepository, SectionRepository sectionRepository, UserRepository userRepository) {
        this.exerciseRepository = exerciseRepository;
        this.sectionRepository = sectionRepository;
        this.userRepository = userRepository;
    }

    public List<Exercise> findAllExercisesBySection(Long sectionId){
        Section section = sectionRepository.findById(sectionId).get();
        return exerciseRepository.findAllBySection(section);
    }

    public List<ExerciseDTO> getAllExercises(){
        List<Section> sections = sectionRepository.findAll();
        return sections.stream().map(s -> new ExerciseDTO(s.getId(),
                s.getName(),
                s.getDescription(),
                exerciseRepository.findAllBySection(s))).toList();
    }

    public Exercise findExerciseById(Long id){
        return exerciseRepository.findById(id).get();
    }

    public void doExercise(Long userId, Long exerciseId, int level){
        User user = userRepository.findById(userId).get();
        UserDetails userDetails = user.getUserDetails();
        List<ExercisesDone> exercisesDone = userDetails.getExercisesDone();
        ExercisesDone exercise = new ExercisesDone(exerciseId, LocalDateTime.now(), Level.values()[level]);
        exercisesDone.add(exercise);
        userDetails.setExercisesDone(exercisesDone);
        userRepository.save(user);
        addPoints(userId, exerciseId, level);
    }

    public void addPoints(Long userId, Long exerciseId, int level){
        Exercise exercise = exerciseRepository.findById(exerciseId).get();
        User user = userRepository.findById(userId).get();
        user.getUserDetails().setPoints(user.getUserDetails().getPoints()+exercise.getPoints()*(level+1));
        userRepository.save(user);
        if(user.getUserDetails().getLevel()!=Level.MASTER){
            calculateLevel(userId); //nie ma sensu sprawdzac przy maks poziomie
        }
    }

    public void calculateLevel(Long userId){
        User user = userRepository.findById(userId).get();
        int points = user.getUserDetails().getPoints();
        if(points>1000 && points<2000){
            user.getUserDetails().setLevel(Level.INTERMEDIATE);
        }
        if(points>=2000 && points<3000){
            user.getUserDetails().setLevel(Level.ADVANCED);
        }
        if(points>=3000){
            user.getUserDetails().setLevel(Level.MASTER);
        }
        userRepository.save(user);
        //przykladowe wartosci,do zmiany
    }

    public void addToFavourites(Long userId, Long exerciseId){
        User user = userRepository.findById(userId).get();
        List<Long> exercises = user.getUserDetails().getFavoriteExercises();
        exercises.add(exerciseId);
        user.getUserDetails().setFavoriteExercises(exercises);
        userRepository.save(user);
    }

    public void removeFromFavourites(Long userId, Long exerciseId){
        User user = userRepository.findById(userId).get();
        List<Long> exercises = user.getUserDetails().getFavoriteExercises();
        exercises.remove(exerciseId);
        user.getUserDetails().setFavoriteExercises(exercises);
        userRepository.save(user);
    }


}
