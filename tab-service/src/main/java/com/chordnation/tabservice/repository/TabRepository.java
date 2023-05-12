package com.chordnation.tabservice.repository;

import com.chordnation.tabservice.domain.Tab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TabRepository extends JpaRepository<Tab, Long> {

}
