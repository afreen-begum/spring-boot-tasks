package com.stackroute.service;
import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
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
        Track track = new Track();
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
        when(trackRepository.save(any())).thenReturn(track);
        Track saveTrack = trackRepository.save(track);
        Assert.assertEquals(track, saveTrack);
        //verifies that trackRepository save method is only called once
        verify(trackRepository, times(1)).save(track);
    }
    @Test
    public void getAllUser() {
        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> userList = trackRepository.findAll();
        Assert.assertEquals(list, userList);
    }
    @Test(expected = NullPointerException.class)
    public void saveUserTestFailure() throws TrackAlreadyExistsException {
        when(trackRepository.save(any())).thenReturn(null);
        Track savedTrack = trackService.saveTrack(track);
        System.out.println("savedTrack" + savedTrack);

    }
}