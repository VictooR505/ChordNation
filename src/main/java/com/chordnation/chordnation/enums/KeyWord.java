package com.chordnation.chordnation.enums;

import java.util.*;
import java.util.stream.Collectors;

public enum KeyWord {
    // Rodzaj ćwiczenia
    CHORD_PRACTICE("Nauka akordów", "Rodzaj ćwiczenia"),
    SCALE_PRACTICE("Ćwiczenie skali", "Rodzaj ćwiczenia"),
    FINGERSTYLE("Fingerstyle", "Rodzaj ćwiczenia"),
    STRUMMING("Uderzanie akordów", "Rodzaj ćwiczenia"),
    PICKING("Picking", "Rodzaj ćwiczenia"),
    ARPEGGIO("Arpeggio", "Rodzaj ćwiczenia"),
    SLIDING("Sliding", "Rodzaj ćwiczenia"),
    HAMMER_ON("Hammer-on", "Rodzaj ćwiczenia"),
    PULL_OFF("Pull-off", "Rodzaj ćwiczenia"),
    BENDING("Bending", "Rodzaj ćwiczenia"),
    TAPPING("Tapping", "Rodzaj ćwiczenia"),

    // Tempo
    SLOW("Wolne tempo", "Tempo"),
    MEDIUM("Średnie tempo", "Tempo"),
    FAST("Szybkie tempo", "Tempo"),

    // Typ utworu
    SOLO("Solo", "Typ utworu"),
    DUET("Duet", "Typ utworu"),
    BAND("Zespół", "Typ utworu"),

    // Techniki
    LEGATO("Legato", "Techniki"),
    STACCATO("Staccato", "Techniki"),
    VIBRATO("Vibrato", "Techniki"),
    PALM_MUTING("Palm muting", "Techniki"),
    HARMONICS("Harmoniki", "Techniki"),
    SWEEP_PICKING("Sweep picking", "Techniki"),

    // Sprzęt
    ACOUSTIC("Akustyczna", "Sprzęt"),
    ELECTRIC("Elektryczna", "Sprzęt"),
    BASS("Basowa", "Sprzęt"),

    // Specjalne
    IMPROVISATION("Improwizacja", "Specjalne"),
    EAR_TRAINING("Trening słuchowy", "Specjalne"),
    MUSIC_THEORY("Teoria muzyki", "Specjalne"),
    RHYTHM("Rytm", "Specjalne"),
    MELODY("Melodia", "Specjalne"),
    TAB_READING("Czytanie tabulatur", "Specjalne"),
    SIGHT_READING("Czytanie nut", "Specjalne"),
    SONGWRITING("Pisanie utworów", "Specjalne");

    private final String description;
    private final String group;

    KeyWord(String description, String group) {
        this.description = description;
        this.group = group;
    }

    public String getDescription() {
        return description;
    }

    public String getGroup() {
        return group;
    }

    public static Map<String, List<KeyWord>> getGroupedByGroup() {
        return Arrays.stream(values())
                .collect(Collectors.groupingBy(KeyWord::getGroup));
    }
}
