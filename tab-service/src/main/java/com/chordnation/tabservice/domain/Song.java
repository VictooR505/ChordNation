package com.chordnation.tabservice.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String artist;
    private String name;
    private String genre;
    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "song_id"))
    private List<Tab> tabs;

    public Song(Long id, String artist, String name, String genre, List<Tab> tabs) {
        this.id = id;
        this.artist = artist;
        this.name = name;
        this.genre = genre;
        this.tabs = tabs;
    }

    public Song() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Tab> getTabs() {
        return tabs;
    }

    public void setTabs(List<Tab> tabs) {
        this.tabs = tabs;
    }
}
