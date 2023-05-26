package com.chordnation.tabservice.repository;

import com.chordnation.tabservice.domain.Song;
import com.chordnation.tabservice.domain.enums.GuitarType;
import com.chordnation.tabservice.domain.enums.Level;
import com.chordnation.tabservice.domain.enums.TabType;
import com.chordnation.tabservice.domain.enums.Tuning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    @Query("SELECT s FROM Song s WHERE s.artist IN (:artists) AND s.genre IN (:genres)")
    List<Song> findAllByArtistsAndGenres(List<String> artists, List<String> genres);
    @Query("SELECT s FROM Song s JOIN s.tabs t WHERE s.artist IN (:artists) AND s.genre IN (:genres) AND t.level IN (:level) AND t.tabType IN (:tabType) AND t.guitarType IN (:guitarType) AND t.tuning IN (:tuning)")
    List<Song> findAllWithFilters(List<String> artists, List<String> genres, List<Level> level, List<TabType> tabType,
                                  List<GuitarType> guitarType, List<Tuning> tuning);
    @Query("SELECT DISTINCT s.artist FROM Song s")
    List<String> getAllArtists();

    @Query("SELECT DISTINCT s.genre FROM Song s")
    List<String> getAllGenres();
}
