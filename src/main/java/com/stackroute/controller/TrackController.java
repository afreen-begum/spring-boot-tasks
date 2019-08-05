package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//s a specialized version of the controller. It includes the @Controller and @ResponseBody annotations and as a result, simplifies the controller implementation
@RequestMapping("api/v1/")
public class TrackController {
    private TrackService trackService;
    private ResponseEntity responseEntity;

    public TrackController() {
    }

    @Autowired
    //TrackController constructor is generated
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping("track")
//to save a track
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackAlreadyExistsException{

        //try {
            trackService.saveTrack(track);
            responseEntity=new ResponseEntity<String>("successfully posted",HttpStatus.CREATED);

       // } catch (Exception e) {


            //responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);


        //}
        return responseEntity;

    }

    @GetMapping("track/{id}")
    //to get a track by its id
    public ResponseEntity<?> getTrackById(@PathVariable int id) throws TrackNotFoundException {
        //try {
            responseEntity = new ResponseEntity<Track>(trackService.getTrackById(id), HttpStatus.OK);
       // } catch (Exception e) {
           // responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
       // }
        return responseEntity;
    }

    @DeleteMapping("track/{id}")
    //to delete the track by its id
    public ResponseEntity<?> deleteTrackById(@PathVariable("id") int id) throws TrackNotFoundException{
        //try {
            responseEntity = new ResponseEntity<Optional<Track>>(trackService.deleteTrackById(id), HttpStatus.OK);
       // } catch (Exception e) {
            //responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        //}
        return responseEntity;}

        @GetMapping("track")
        public ResponseEntity<?> getAllTracks ()throws Exception {
            //try {
                responseEntity = new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.OK);
           // } catch (Exception e) {
               // responseEntity = new ResponseEntity(new Exception("Internal Server Error"), HttpStatus.CONFLICT);
           // }
            return responseEntity;
        }
        @PutMapping("track/{id}")
        //to update the track
        public ResponseEntity<?> updateTrackById ( @PathVariable int id, @RequestBody Track track)throws TrackNotFoundException{
           // try {
                responseEntity = new ResponseEntity(trackService.updateTrackById(id, track), HttpStatus.OK);
           // } catch (Exception e) {
               // responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
           // }
            return responseEntity;
        }
        @GetMapping("trackname/{name}")
        //to get track by its name
        public ResponseEntity<?> getTrackByName (@PathVariable String name)throws TrackNotFoundException{
            //try {
                trackService.getTrackByName(name);
                responseEntity = new ResponseEntity<String>("name not exists", HttpStatus.OK);
           // } catch (Exception e) {
                //responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
            //}
            return responseEntity;

        }
    }



