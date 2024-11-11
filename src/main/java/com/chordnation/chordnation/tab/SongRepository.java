package com.chordnation.chordnation.tab;

import com.chordnation.chordnation.enums.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

/*    @Query("SELECT s FROM Song s LEFT JOIN s.tabs t WHERE (s.genre IN (:genres)) AND (t.level IN (:levels))")
    List<Song> findAll(List<String> genres, List<Level> levels);*/

    @Query("SELECT DISTINCT s.genre FROM Song s")
    List<String> getAllGenres();

    @Query("SELECT DISTINCT s.artist FROM Song s")
    List<String> getAllArtists();

/*    @Query("SELECT s FROM Song s LEFT JOIN s s WHERE (s.genre IN (:genres)) AND (t.level IN (:levels)) AND (LOWER(s.artist) LIKE LOWER(CONCAT('%', :artists, '%')) OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%')))")
    List<Song> findAllWithName(List<String> genres, List<Level> levels, @Param("artists") String artists, @Param("name") String name);*/
}
