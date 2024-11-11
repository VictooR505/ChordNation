package com.chordnation.chordnation.tab;

import com.chordnation.chordnation.enums.GuitarType;
import com.chordnation.chordnation.enums.Level;
import com.chordnation.chordnation.enums.TabType;
import com.chordnation.chordnation.enums.Tuning;
import jakarta.persistence.*;

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
    private double rate;
    private double rateNumber;
    private String url;

    public Tab(Long id, Song song,/* Long version,*/ Level level, TabType tabType, GuitarType guitarType, Tuning tuning, double rate, double rateNumber, String url) {
        this.id = id;
        this.song = song;
    //    this.version = version;
        this.level = level;
        this.tabType = tabType;
        this.guitarType = guitarType;
        this.tuning = tuning;
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

}
