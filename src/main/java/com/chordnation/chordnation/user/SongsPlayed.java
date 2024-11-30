package com.chordnation.chordnation.user;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class SongsPlayed {
    private Long songId;
    private LocalDateTime playDate;

    public SongsPlayed() {
    }

    public SongsPlayed(Long songId, LocalDateTime playDate) {
        this.songId = songId;
        this.playDate = playDate;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public LocalDateTime getPlayDate() {
        return playDate;
    }

    public void setPlayDate(LocalDateTime playDate) {
        this.playDate = playDate;
    }
}
