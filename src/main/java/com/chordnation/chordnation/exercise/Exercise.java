package com.chordnation.chordnation.exercise;

import com.chordnation.chordnation.enums.Level;
import jakarta.persistence.*;

@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;
    private Level level;
    private String name;
    private String approxDuration;
    private String description;
    private String audioUrl;
    private int points;
    private int requiredPoints;
    //5 sekcji + 5/6 cw na kazda sekcji

    public Exercise(Long id, Section section, Level level, String name, String approxDuration, String description, String audioUrl, int points, int requiredPoints) {
        this.id = id;
        this.section = section;
        this.level = level;
        this.name = name;
        this.approxDuration = approxDuration;
        this.description = description;
        this.audioUrl = audioUrl;
        this.points = points;
        this.requiredPoints = requiredPoints;
    }

    public Exercise() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public String getApproxDuration() {
        return approxDuration;
    }

    public void setApproxDuration(String approxDuration) {
        this.approxDuration = approxDuration;
    }

    public int getRequiredPoints() {
        return requiredPoints;
    }

    public void setRequiredPoints(int requiredPoints) {
        this.requiredPoints = requiredPoints;
    }
}
