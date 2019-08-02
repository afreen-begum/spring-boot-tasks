package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService {
    private TrackRepository trackRepository;
    //TrackServiceImpl constructor is created
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    //to save track
    public Track saveTrack(Track track) {
        Track savedTrack = trackRepository.save(track);
        return savedTrack;
    }

    @Override
    //to get a track by its id
    public Track getTrackById(int id) {
        Track retrievedTrack=trackRepository.findById(id).get();
        return retrievedTrack;
    }

    @Override
    //to delete a track by its id
        public Optional<Track> deleteTrackById(int id) {
        Optional<Track> optional=trackRepository.findById(id);
        trackRepository.deleteById(id);
        if(optional.isPresent())
        {

        }
            return optional;
    }

    @Override
    //get all the tracks
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    //update a particular  part of a track by its id
        public Track updateTrackById(int id, Track track) {
//        delete the track
            trackRepository.deleteById(id);
//        edit the track and save it
            Track updateTrackById = trackRepository.save(track);
            return updateTrackById;
        }
    }

