package com.chordnation.tabservice.repository;

import com.chordnation.tabservice.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    @Query("SELECT s FROM Song s WHERE s.artist IN (:artists) OR s.genre IN (:genres)")
    List<Song> findAllByArtistsAndGenres(List<String> artists, List<String> genres);
}
