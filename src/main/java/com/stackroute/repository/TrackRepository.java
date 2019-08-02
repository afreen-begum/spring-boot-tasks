package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface TrackRepository extends JpaRepository<Track,Integer> {
    @Query("select t from Track t where t.name = ?1")
    public Track findByName(String name);


}
