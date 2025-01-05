package com.chordnation.chordnation.enums;

public enum KeyWord {
    // Rodzaj ćwiczenia
    CHORD_PRACTICE("Nauka akordów"),
    SCALE_PRACTICE("Ćwiczenie skali"),
    FINGERSTYLE("Fingerstyle"),
    STRUMMING("Uderzanie akordów"),
    PICKING("Picking"),
    ARPEGGIO("Arpeggio"),
    SLIDING("Sliding"),
    HAMMER_ON("Hammer-on"),
    PULL_OFF("Pull-off"),
    BENDING("Bending"),
    TAPPING("Tapping"),

    // Tempo
    SLOW("Wolne tempo"),
    MEDIUM("Średnie tempo"),
    FAST("Szybkie tempo"),

    // Typ utworu
    SOLO("Solo"),
    DUET("Duet"),
    BAND("Zespół"),

    // Techniki
    LEGATO("Legato"),
    STACCATO("Staccato"),
    VIBRATO("Vibrato"),
    PALM_MUTING("Palm muting"),
    HARMONICS("Harmoniki"),
    SWEEP_PICKING("Sweep picking"),

    // Sprzęt
    ACOUSTIC("Akustyczna"),
    ELECTRIC("Elektryczna"),
    BASS("Basowa"),

    // Specjalne
    IMPROVISATION("Improwizacja"),
    EAR_TRAINING("Trening słuchowy"),
    MUSIC_THEORY("Teoria muzyki"),
    RHYTHM("Rytm"),
    MELODY("Melodia"),
    TAB_READING("Czytanie tabulatur"),
    SIGHT_READING("Czytanie nut"),
    SONGWRITING("Pisanie utworów");

    private final String description;

    KeyWord(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
