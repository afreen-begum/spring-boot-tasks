package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class TrackController {
    private TrackService trackService;

    public TrackController() {
    }

    @Autowired
    //TrackController constructor is generated
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }
@PostMapping("track")
//to save a track
    public ResponseEntity<?> saveTrack(@RequestBody Track track) {
        Track savedTrack = trackService.saveTrack(track);
        return new ResponseEntity<>(savedTrack, HttpStatus.OK);
    }
    @GetMapping("track/{id}")
    //to get a track by its id
    public ResponseEntity<?> getTrackById(@PathVariable int id) {
        System.out.println(id);
        Track retrivedTrack = trackService.getTrackById(id);
        return new ResponseEntity<Track>(retrivedTrack, HttpStatus.FOUND);
    }
    @DeleteMapping("track/{id}")
    //to delete the track by its id
    public ResponseEntity<?> deleteTrackById(@PathVariable("id") int id) {
        Optional<Track> tracksList = (Optional<Track>) trackService.deleteTrackById(id);
        return new ResponseEntity<>(tracksList,HttpStatus.GONE);
    }
    @GetMapping("track")
    //to  get all the tracks
    public ResponseEntity<?> getAllTracks() {
        return new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.FOUND);

    }
    @PatchMapping("track/{id}")
    //to update the track
    public ResponseEntity<?> updateTrackById(@PathVariable int id, @RequestBody Track track) {
        Track trackUpdated = trackService.updateTrackById(id, track);
        return new ResponseEntity<>(trackUpdated, HttpStatus.ACCEPTED);

    }
}

