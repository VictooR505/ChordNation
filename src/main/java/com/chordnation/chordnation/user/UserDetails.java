package com.chordnation.chordnation.user;

import com.chordnation.chordnation.enums.GuitarType;
import com.chordnation.chordnation.enums.Level;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;

import java.util.List;
import java.util.Map;

@Embeddable
public class UserDetails {
    private Level level;
    private GuitarType guitarType;
    @ElementCollection
    private List<String> favouriteGenres;
    @ElementCollection
    private List<String> favouriteArtists;
    @ElementCollection
    private List<Long> favouriteSongs;
    private int points;
    @ElementCollection
    private Map<Long, Integer> exercisesDone; //klucz - id cwiczenia, wartosc - stopien opanowania

    public UserDetails(Level level, GuitarType guitarType, List<String> favouriteGenres, List<String> favouriteArtists, List<Long> favouriteSongs, int points) {
        this.level = level;
        this.guitarType = guitarType;
        this.favouriteGenres = favouriteGenres;
        this.favouriteArtists = favouriteArtists;
        this.favouriteSongs = favouriteSongs;
        this.points = points;
    }

    public UserDetails() {
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public GuitarType getGuitarType() {
        return guitarType;
    }

    public void setGuitarType(GuitarType guitarType) {
        this.guitarType = guitarType;
    }

    public List<String> getFavouriteGenres() {
        return favouriteGenres;
    }

    public void setFavouriteGenres(List<String> favouriteGenres) {
        this.favouriteGenres = favouriteGenres;
    }

    public List<String> getFavouriteArtists() {
        return favouriteArtists;
    }

    public void setFavouriteArtists(List<String> favouriteArtists) {
        this.favouriteArtists = favouriteArtists;
    }

    public List<Long> getFavouriteSongs() {
        return favouriteSongs;
    }

    public void setFavouriteSongs(List<Long> favouriteSongs) {
        this.favouriteSongs = favouriteSongs;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Map<Long, Integer> getExercisesDone() {
        return exercisesDone;
    }

    public void setExercisesDone(Map<Long, Integer> exercisesDone) {
        this.exercisesDone = exercisesDone;
    }
}
