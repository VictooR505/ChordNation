package com.chordnation.chordnation.tab;

import com.chordnation.chordnation.enums.*;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

@Entity
public class Tab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "song_id")
    private Song song;
   // private Long version;
    private Level level;
    private TabType tabType;
    private GuitarType guitarType;
    private Tuning tuning;
    @ElementCollection
    private List<KeyWord> keyWords;
    private double rate;
    private double rateNumber;
    private String url;

    public Tab(Long id, Song song,/* Long version,*/ Level level, TabType tabType, GuitarType guitarType, Tuning tuning, List<KeyWord> keyWords, double rate, double rateNumber, String url) {
        this.id = id;
        this.song = song;
    //    this.version = version;
        this.level = level;
        this.tabType = tabType;
        this.guitarType = guitarType;
        this.tuning = tuning;
        this.keyWords = keyWords;
        this.rate = rate;
        this.rateNumber = rateNumber;
        this.url = url;
    }

    public Tab() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

/*    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }*/

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public TabType getTabType() {
        return tabType;
    }

    public void setTabType(TabType tabType) {
        this.tabType = tabType;
    }

    public GuitarType getGuitarType() {
        return guitarType;
    }

    public void setGuitarType(GuitarType guitarType) {
        this.guitarType = guitarType;
    }

    public Tuning getTuning() {
        return tuning;
    }

    public void setTuning(Tuning tuning) {
        this.tuning = tuning;
    }

    public List<KeyWord> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<KeyWord> keyWords) {
        this.keyWords = keyWords;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getRateNumber() {
        return rateNumber;
    }

    public void setRateNumber(double rateNumber) {
        this.rateNumber = rateNumber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

}
