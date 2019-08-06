package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@Document
public interface TrackRepository extends MongoRepository<Track,Integer> {
    //@Query("select t from Track t where t.name = ?1")
    public Track findByName(String name);

}
