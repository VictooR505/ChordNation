package com.chordnation.chordnation.tab;

import com.chordnation.chordnation.enums.Genre;
import com.chordnation.chordnation.enums.GuitarType;
import com.chordnation.chordnation.enums.KeyWord;
import com.chordnation.chordnation.enums.Level;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TabRepository extends JpaRepository<Tab, Long> {

    List<Tab> findAllBySong(Song song);

    @Query("SELECT t FROM Tab t JOIN t.song s WHERE (s.genre IN (:genres)) AND (t.level IN (:levels)) AND (t.guitarType IN (:guitarTypes))")
    List<Tab> findAll(List<Genre> genres, List<Level> levels, List<GuitarType> guitarTypes, Sort by);

    @Query("SELECT t FROM Tab t JOIN t.song s WHERE (s.genre IN (:genres)) AND (t.level IN (:levels)) OR (s.artist IN (:artists)) OR (t.keyWords IN (:keyWords))")
    List<Tab> findAllSuggested(List<Genre> genres, List<Level> levels, List<String> artists, List<KeyWord> keyWords);

    @Query("SELECT t FROM Tab t JOIN t.song s WHERE (s.genre IN (:genres)) AND (t.level IN (:levels)) " +
            "AND (LOWER(s.artist) LIKE LOWER(CONCAT('%', :artists, '%')) OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND (t.guitarType IN (:guitarTypes))")
    List<Tab> findAllWithName(List<Genre> genres, List<Level> levels, List<GuitarType> guitarTypes,  @Param("artists") String artists, @Param("name") String name, Sort by);
}
