package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrackServiceTest {
    Track track;
    //Create a mock for UserRepository
    @Mock
    TrackRepository trackRepository;
    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    TrackServiceImpl trackService;
    List<Track> list = null;

    @Before
    public void setUp() throws Exception {
//Initialising the mock object
        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setId(10);
        track.setName("track name");
        track.setComment("comment1");
        list = new ArrayList<>();
        list.add(track);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void saveTrackSuccess() throws TrackAlreadyExistsException {
        when(trackRepository.existsById(track.getId())).thenReturn(false);
        when(trackRepository.save(any())).thenReturn(track);
        Track saveTrack = trackService.saveTrack(track);
        Assert.assertEquals(track, saveTrack);
        //verifies that trackRepository save method is only called once
        verify(trackRepository, times(1)).save(track);
    }

    @Test
    public void getAllTracks() {
        trackRepository.save(track);
        //injecting the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> trackList = trackRepository.findAll();
        Assert.assertEquals(list, trackList);
    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void saveTrackTestFailure() throws TrackAlreadyExistsException {
        when(trackRepository.save(any())).thenReturn(null);
        Track savedTrack = trackService.saveTrack(track);
        System.out.println("savedTrack" + savedTrack);

    }

    @Test
    public void getTrackByName() throws TrackNotFoundException {
        trackRepository.save(track);
        when(trackRepository.findByName(any())).thenReturn(track);
        Track findByName = trackService.getTrackByName("afreen");

    }

    @Test(expected = TrackNotFoundException.class)
    public void getTrackById() throws TrackNotFoundException {
        trackRepository.save(track);
        when(trackRepository.existsById(10)).thenReturn(true);
        when(trackRepository.findById(10)).thenReturn(Optional.of(track));
        Track findById = trackService.getTrackById(10);
        Assert.assertEquals(track, findById);
    }

    @Test
    public void updateTrackById() throws TrackNotFoundException {
        trackRepository.save(track);

        when(trackRepository.findById(1)).thenReturn(Optional.of(track));
        when(trackRepository.save(track)).thenReturn(track);
        Track updateTrack = trackService.updateTrackById(1, track);
        Assert.assertEquals(track, updateTrack);
    }

    @Test
    public void deleteTrackById() throws TrackNotFoundException {
        trackRepository.save(track);
        when(trackRepository.existsById(track.getId())).thenReturn(true);
        when(trackRepository.findById(track.getId())).thenReturn(Optional.of(track));
        Optional<Track> deleteTrack = trackService.deleteTrackById(track.getId());
        Assert.assertEquals(true, trackRepository.existsById(track.getId()));
    }
}