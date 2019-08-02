package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TrackRepository extends JpaRepository<Track,Integer> {
    @Query("select t from Track t where t.name = ?1")
    public Track getTrackByName(String name);

}
