package com.chordnation.chordnation.exercise;

import com.chordnation.chordnation.enums.Level;
import com.chordnation.chordnation.user.User;
import com.chordnation.chordnation.user.UserDetails;
import com.chordnation.chordnation.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public List<Section> getAllExercises(){
        return sectionRepository.findAll();
    }

    public Exercise findExerciseById(Long id){
        return exerciseRepository.findById(id).get();
    }

    public void doExercise(Long userId, Long exerciseId, int level){
        User user = userRepository.findById(userId).get();
        UserDetails userDetails = user.getUserDetails();
        Map<Long, Integer> exercisesDone = userDetails.getExercisesDone();
        exercisesDone.put(exerciseId, level);
        userDetails.setExercisesDone(exercisesDone);
        userRepository.save(user);
        addPoints(userId, exerciseId);
    }

    public void addPoints(Long userId, Long exerciseId){
        Exercise exercise = exerciseRepository.findById(exerciseId).get();
        User user = userRepository.findById(userId).get();
        user.getUserDetails().setPoints(user.getUserDetails().getPoints()+exercise.getPoints());
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
        //przykladowe wartosci,do zmiany
    }


}
