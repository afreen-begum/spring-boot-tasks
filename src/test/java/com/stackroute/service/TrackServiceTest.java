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
        track = null;
        list = null;
        trackRepository.deleteAll();
    }

    @Test
    public void givenInputShouldReturnSaveTrackSuccess() throws TrackAlreadyExistsException {

        when(trackRepository.save(any())).thenReturn(track);
        Track savedTrack = trackService.saveTrack(track);
        assertEquals(track,savedTrack);

        verify(trackRepository,times(1)).save(track);

    }

    @Test
    public void givenInputShouldReturnSaveTrackChecksForNotNull() throws TrackAlreadyExistsException {

        when(trackRepository.save(any())).thenReturn(track);
        Track savedTrack1 = trackService.saveTrack(track);

        assertNotNull(savedTrack1);

        verify(trackRepository,times(1)).save(track);

    }

    @Test
    public void givenInputShouldReturnGetByIdSuccess() throws TrackNotFoundException {

        trackRepository.save(track);

        when(trackRepository.existsById(track.getId())).thenReturn(true);
        when(trackRepository.findById(track.getId())).thenReturn(java.util.Optional.of(track));
        Track getTrack = trackService.getById(track.getId());
        assertEquals(track,getTrack);

        verify(trackRepository,times(2)).findById(track.getId());

    }

    @Test(expected = TrackNotFoundException.class)
    public void givenInputShouldReturnGetByIdFailure() throws TrackNotFoundException {

        trackRepository.save(track);

        when(trackRepository.existsById(track.getId())).thenReturn(true);
        when(trackRepository.findById(track.getId())).thenReturn(java.util.Optional.of(track));
        Track getTrack = trackService.getById(24);
        assertEquals(track,getTrack);

        verify(trackRepository,times(2)).findById(track.getId());
    }

    @Test
    public void givenInputShouldReturnAllTracksSuccess() throws Exception {

        trackRepository.save(track);

        when(trackRepository.findAll()).thenReturn(list);
        List<Track> savedList = trackService.getAllTracks();
        assertEquals(list,savedList);

        verify(trackRepository,times(2)).findAll();
    }

    @Test
    public void givenInputShouldDeleteTrackByIdSuccess() throws TrackNotFoundException {
        trackRepository.save(track);

        when(trackRepository.existsById(track.getId())).thenReturn(true);
        when(trackRepository.findById(track.getId())).thenReturn(java.util.Optional.of(track));

        Optional<Track> track1 = trackService.deleteTrackById(track.getId());

        assertEquals(true,trackRepository.existsById(track.getId()));

        verify(trackRepository,times(1)).deleteById(track.getId());

    }

    @Test(expected = TrackNotFoundException.class)
    public void givenInputShouldDeleteTrackByIdException() throws TrackNotFoundException {
        trackRepository.save(track);

        when(trackRepository.existsById(track.getId())).thenReturn(true);
        when(trackRepository.findById(24)).thenReturn(java.util.Optional.of(track));

        Optional<Track> track1 = trackService.deleteTrackById(track.getId());

        assertEquals(true,trackRepository.existsById(24));

        verify(trackRepository,times(1)).deleteById(track.getId());

    }@Test(expected = TrackNotFoundException.class)
    public void givenInputShouldUpdateTrackException() throws TrackNotFoundException {

        trackRepository.save(track);
        Track t1 = new Track();
        t1.setName("Better");
        t1.setComments("soft acoustic");

        when(trackRepository.findById(track.getId())).thenReturn(Optional.of(track));

        Track trackUpdated =  trackService.updateTrack(24,t1);

        verify(trackRepository,times(2)).findById(track.getId());

    }

    @Test
    public void givenInputShouldReturnTrackByNameSuccess() throws TrackNotFoundException {

        trackRepository.save(track);

        when(trackRepository.findByName(track.getName())).thenReturn(track);

        Track searchedName = trackService.getByName("soul");

        assertSame(searchedName,track);

        verify(trackRepository,times(2)).findByName(track.getName());
    }

    @Test(expected = NullPointerException.class)
    public void givenInputShouldReturnTrackByNameException() throws TrackNotFoundException {

        trackRepository.save(track);

        when(trackRepository.findByName(track.getName())).thenReturn(track);

        Track searchedName = trackService.getByName("jagsdugasduasi");
        searchedName.getId();
        verify(trackRepository,times(2)).findById(track.getId());
    }
}
    @Test(expected = TrackNotFoundException.class)
    public void givenInputShouldUpdateTrackException() throws TrackNotFoundException {

        trackRepository.save(track);
        Track t1 = new Track();
        t1.setName("Better");
        t1.setComments("soft acoustic");

        when(trackRepository.findById(track.getId())).thenReturn(Optional.of(track));

        Track trackUpdated =  trackService.updateTrack(24,t1);

        verify(trackRepository,times(2)).findById(track.getId());

    }

    @Test
    public void givenInputShouldReturnTrackByNameSuccess() throws TrackNotFoundException {

        trackRepository.save(track);

        when(trackRepository.findByName(track.getName())).thenReturn(track);

        Track searchedName = trackService.getByName("soul");

        assertSame(searchedName,track);

        verify(trackRepository,times(2)).findByName(track.getName());
    }

    @Test(expected = NullPointerException.class)
    public void givenInputShouldReturnTrackByNameException() throws TrackNotFoundException {

        trackRepository.save(track);

        when(trackRepository.findByName(track.getName())).thenReturn(track);

        Track searchedName = trackService.getByName("rock on");
        searchedName.getId();
        verify(trackRepository,times(2)).findById(track.getId());
    }
}
