package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
//@Profile("prod")
public class TrackServiceImpl implements TrackService {
    private TrackRepository trackRepository;
    //TrackServiceImpl constructor is created


    public TrackServiceImpl() {
    }

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    //to save track
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if(trackRepository.existsById(track.getId())) {
            throw new TrackAlreadyExistsException("track already exists");

        }
        Track savedTrack = trackRepository.save(track);
        if(savedTrack == null) {
            throw new TrackAlreadyExistsException("track already exists");
        }
        return savedTrack;
    }

    @Override
    //to get a track by its id
    public Track getTrackById(int id) throws TrackNotFoundException {
        if(trackRepository.findById(id).isPresent()) {
            throw new TrackNotFoundException("track id doesnt found");
        }
        Track retrievedTrack=trackRepository.findById(id).get();
        if(retrievedTrack == null) {
            throw new TrackNotFoundException("track id doesnt found");
        }
        return retrievedTrack;
    }

    @Override
    //to delete a track by its id
        public Optional<Track> deleteTrackById(int id) throws TrackNotFoundException {
        Optional<Track> optional = trackRepository.findById(id);
        if (optional.isPresent()) {
            trackRepository.deleteById(id);
        }
        else
            throw new TrackNotFoundException("Track Not Found");
        return optional;
    }

    @Override
    //get all the tracks
    public List<Track> getAllTracks() throws Exception {
        if (trackRepository.findAll().isEmpty()){
            throw new Exception("No Tracks Available");
        }
        return trackRepository.findAll();
    }

    @Override
    //update a particular  part of a track by its id
        public Track updateTrackById(int id, Track track) throws TrackNotFoundException  {
        if (!trackRepository.findById(id).isPresent()){

            throw new TrackNotFoundException("Track Not Found");
        }
        Track update = trackRepository.findById(id).get();
        update.setName(track.getName());
        update.setComment(track.getComment());
        return trackRepository.save(track);


        }
        @Override
        //to get a track by its name
       public Track getTrackByName(String name)throws TrackNotFoundException {
            Track   dbName = trackRepository.getTrackByName(name);
            if (dbName == null){
                throw new TrackNotFoundException("Track Not Found");
            }
            return dbName;
        }
    }

