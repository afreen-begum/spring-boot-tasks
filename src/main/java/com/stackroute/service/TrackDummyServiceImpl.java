package com.stackroute.service;
import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
/*@Qualifier("dummyImplementation")*/
//Inorder to run this implementation class either we can use @Primary annotation or we can specify in @Qualifier
//@Profile("dev")
public class TrackDummyServiceImpl implements TrackService {

    TrackRepository trackRepository;



    public TrackDummyServiceImpl() {
    }

    @Autowired
    public TrackDummyServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        return null;
    }

    @Override
    public Track getTrackById(int id) throws TrackNotFoundException {
        return null;
    }

    @Override
    public Optional<Track> deleteTrackById(int id) throws TrackNotFoundException {
        return Optional.empty();
    }

    @Override
    public List<Track> getAllTracks() throws Exception {
        return null;
    }

    @Override
    public Track updateTrackById(int id, Track track) throws TrackNotFoundException {
        return null;
    }

    @Override
    public Track getTrackByName(String name) throws TrackNotFoundException {
        return null;
    }
}