package com.chordnation.chordnation.user;

import com.chordnation.chordnation.enums.Genre;
import com.chordnation.chordnation.enums.GuitarType;
import com.chordnation.chordnation.enums.KeyWord;
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
    private List<Genre> favouriteGenres;
    @ElementCollection
    private List<String> favouriteArtists;
    @ElementCollection
    private List<Long> favoriteSongs;
    @ElementCollection
    private List<Long> favoriteExercises;
    private int points;
    @ElementCollection
    private List<ExercisesDone> exercisesDone;
    @ElementCollection
    private List<SongsPlayed> songsPlayed;
    @ElementCollection
    private List<KeyWord> keyWords;
    @ElementCollection
    private Map<Long, Integer> ratedTabs;
    private boolean firstLogin;

    public UserDetails(Level level, GuitarType guitarType, List<Genre> favouriteGenres, List<String> favoriteArtists, List<Long> favouriteSongs, List<Long> favoriteExercises, int points, List<KeyWord> keyWords, Map<Long, Integer> ratedTabs, boolean firstLogin) {
        this.level = level;
        this.guitarType = guitarType;
        this.favouriteGenres = favouriteGenres;
        this.favouriteArtists = favoriteArtists;
        this.favoriteSongs = favouriteSongs;
        this.favoriteExercises = favoriteExercises;
        this.points = points;
        this.keyWords = keyWords;
        this.ratedTabs = ratedTabs;
        this.firstLogin = firstLogin;
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

    public List<Genre> getFavouriteGenres() {
        return favouriteGenres;
    }

    public void setFavouriteGenres(List<Genre> favouriteGenres) {
        this.favouriteGenres = favouriteGenres;
    }

    public List<String> getFavouriteArtists() {
        return favouriteArtists;
    }

    public void setFavouriteArtists(List<String> favouriteArtists) {
        this.favouriteArtists = favouriteArtists;
    }

    public List<Long> getFavoriteSongs() {
        return favoriteSongs;
    }

    public void setFavoriteSongs(List<Long> favoriteSongs) {
        this.favoriteSongs = favoriteSongs;
    }

    public List<Long> getFavoriteExercises() {
        return favoriteExercises;
    }

    public void setFavoriteExercises(List<Long> favoriteExercises) {
        this.favoriteExercises = favoriteExercises;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<ExercisesDone> getExercisesDone() {
        return exercisesDone;
    }

    public void setExercisesDone(List<ExercisesDone> exercisesDone) {
        this.exercisesDone = exercisesDone;
    }

    public List<SongsPlayed> getSongsPlayed() {
        return songsPlayed;
    }

    public void setSongsPlayed(List<SongsPlayed> songsPlayed) {
        this.songsPlayed = songsPlayed;
    }

    public List<KeyWord> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<KeyWord> keyWords) {
        this.keyWords = keyWords;
    }

    public Map<Long, Integer> getRatedTabs() {
        return ratedTabs;
    }

    public void setRatedTabs(Map<Long, Integer> ratedTabs) {
        this.ratedTabs = ratedTabs;
    }

    public boolean isFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(boolean firstLogin) {
        this.firstLogin = firstLogin;
    }
}
