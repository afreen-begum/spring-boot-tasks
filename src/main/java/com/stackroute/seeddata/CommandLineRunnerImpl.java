package com.stackroute.seeddata;
import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private TrackRepository trackRepository;
    @Autowired
    public CommandLineRunnerImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        Track track1=new Track(1,"afreen","good");
        trackRepository.save(track1);
        Track track2=new Track(3,"shaik","awesome");
        trackRepository.save(track2);
    }
}