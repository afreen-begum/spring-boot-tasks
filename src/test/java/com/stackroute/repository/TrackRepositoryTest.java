package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrackRepositoryTest {
    @Autowired
    TrackRepository trackRepository;
    Track track;

    @Before
    public void setUp() {
        track = new Track();
        track.setId(1);
        track.setName("closer");
        track.setComment("love song");
    }

    @After
    public void tearDown() {
        trackRepository.deleteAll();
    }

    @Test
    public void testToNotSaveAnyTracks() {

        Track newTrack = new Track(12, "Melody", "Happy Evening");
        trackRepository.save(newTrack);
        assertNotSame(track, newTrack);
    }

    @Test
    public void testToGetAllTheTracks() {
        Track t1 = new Track(2, "closer", "love song");
        Track t2 = new Track(3, "taki-taki", "hiphop");
        Track t3 = new Track(4, "let me love you", "justin bieber");
        trackRepository.save(t1);
        trackRepository.save(t2);
        trackRepository.save(t3);

        List<Track> trackList = trackRepository.findAll();

        assertEquals("let me love you", trackList.get(2).getName());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testToGetAllTheTracksThrowsException() {
        Track t1 = new Track(2, "closer", "love song");
        Track t2 = new Track(3, "taki-taki", "hiphop");
        Track t3 = new Track(4, "let me love you", "justin bieber");
        trackRepository.save(t1);
        trackRepository.save(t2);
        trackRepository.save(t3);

        List<Track> trackList = trackRepository.findAll();

        assertEquals("taki-taki", trackList.get(5).getName());
    }

    @Test
    public void testToGetTrackById() {
        Track t1 = new Track();
        Track t2 = new Track(2, "closer", "love song");
        Track t3 = new Track(3, "taki-taki", "hiphop");
        trackRepository.save(t2);
        trackRepository.save(t3);
        t1.setId(3);
        Track t4 = trackRepository.findById(t1.getId()).get();

        assertEquals(t3, t4);

    }

    @Test
    public void testToGetTrackByIdThrowsException() {
        Track t1 = new Track();
        Track t2 = new Track(3, "closer", "lovesong");
        Track t3 = new Track(4, "taki-taki", "hiphop");
        trackRepository.save(t2);
        trackRepository.save(t3);
        t1.setId(4);
        trackRepository.findById(t1.getId()).get();

    }

    @Test
    public void testToDeleteTrackByIdAndReturnsException() {
        Track t1 = new Track(2, "closer", "love song");
        trackRepository.save(t1);
        assertNotSame(false, trackRepository.existsById(t1.getId()));
        trackRepository.deleteById(t1.getId());

    }

    @Test
    public void testToUpdateTrackFoundById() {
        Track t1 = new Track(2, "closer", "love song");
        Track t2 = new Track(3, "taki-taki", "hiphop");
        Track t3 = new Track(4, "let me love you", "justin bieber");
        trackRepository.save(t1);
        trackRepository.save(t2);
        trackRepository.save(t3);

        Track trackList = trackRepository.findById(t2.getId()).get();
        trackList.setName(t3.getName());

        assertEquals(trackList.getName(), t3.getName());

    }

    @Test
    public void testToCheckForUpdateIsNotNull() {
        assertNotNull(trackRepository.existsById(track.getId()));
    }

    @Test
    public void testToFindByNameChecksForTrackName() {

        trackRepository.save(track);
        Track t1 = trackRepository.findByName(track.getName());
        assertEquals("closer", t1.getName());


    }

    @Test
    public void testToCheckFindByNameIsNotEqual() {

        trackRepository.save(track);

        Track t1 = trackRepository.findByName(track.getName());
        assertNotEquals("taki-taki", t1.getName());

    }

}
