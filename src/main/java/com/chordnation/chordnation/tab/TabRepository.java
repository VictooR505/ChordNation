package com.chordnation.chordnation.tab;

import com.chordnation.chordnation.enums.Level;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TabRepository extends JpaRepository<Tab, Long> {
    @Query("SELECT t FROM Tab t JOIN t.song s WHERE (s.genre IN (:genres)) AND (t.level IN (:levels))")
    List<Tab> findAll(List<String> genres, List<Level> levels, Sort by);

    @Query("SELECT t FROM Tab t JOIN t.song s WHERE (s.genre IN (:genres)) AND (t.level IN (:levels)) AND (LOWER(s.artist) LIKE LOWER(CONCAT('%', :artists, '%')) OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%')))")
    List<Tab> findAllWithName(List<String> genres, List<Level> levels, @Param("artists") String artists, @Param("name") String name, Sort by);
}
