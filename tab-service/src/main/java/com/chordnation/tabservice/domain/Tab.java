package com.chordnation.tabservice.domain;

import com.chordnation.tabservice.domain.enums.GuitarType;
import com.chordnation.tabservice.domain.enums.Level;
import com.chordnation.tabservice.domain.enums.TabType;
import com.chordnation.tabservice.domain.enums.Tuning;
import jakarta.persistence.*;

@Entity
public class Tab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Level level;
    private TabType tabType;
    private GuitarType guitarType;
    private Tuning tuning;
    private int rate;
    private String url;

    public Tab(Long id, Level level, TabType tabType, GuitarType guitarType, Tuning tuning, int rate, String url) {
        this.id = id;
        this.level = level;
        this.tabType = tabType;
        this.guitarType = guitarType;
        this.tuning = tuning;
        this.rate = rate;
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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
