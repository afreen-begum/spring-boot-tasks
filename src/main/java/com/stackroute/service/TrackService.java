package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;

import java.util.List;
import java.util.Optional;

public interface TrackService  {
    //methods to operate the track
    public Track saveTrack(Track track) throws TrackAlreadyExistsException;
    public Track getTrackById(int id) throws TrackNotFoundException;
    public Optional<Track> deleteTrackById(int id) throws TrackNotFoundException;
    public List<Track> getAllTracks() throws Exception;
    public Track updateTrackById(int id,Track track) throws TrackNotFoundException;
    public Track getTrackByName(String name) throws TrackNotFoundException;
}
