package com.chordnation.userservice.domain;

import com.chordnation.userservice.domain.enums.GuitarType;
import com.chordnation.userservice.domain.enums.Level;
import jakarta.persistence.*;

import java.util.List;

@Embeddable
public class UserDetails {
    private Level level;
    private GuitarType guitarType;
    @ElementCollection
    private List<String> favouriteGenres;
    @ElementCollection
    private List<String> favouriteArtists;

    public UserDetails(Level level, GuitarType guitarType, List<String> favouriteGenres, List<String> favouriteArtists) {
        this.level = level;
        this.guitarType = guitarType;
        this.favouriteGenres = favouriteGenres;
        this.favouriteArtists = favouriteArtists;
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
}
