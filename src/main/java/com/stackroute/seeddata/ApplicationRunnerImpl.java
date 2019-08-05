///*
//package com.stackroute.seeddata;
//import com.stackroute.domain.Track;
//import com.stackroute.repository.TrackRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.ApplicationEvent;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Component;
//import org.springframework.context.ApplicationListener;
//
////It will get execute, before the server get starts
//@Component
//@PropertySource("classpath:application.properties")
//public class ApplicationRunnerImpl implements ApplicationListener {
//    @Value("${track1.id}")
//    int id;
//    @Value("${track1.name}")
//    String name;
//    @Value("${track1.comment}")
//    String comment;
//    //    object of track created
//
//    private TrackRepository trackRepository;
//
//    //    Track repository constructor
//    @Autowired
//    public ApplicationRunnerImpl(TrackRepository trackRepository) {
//        this.trackRepository = trackRepository;
//    }
//
//    //    method over ridden
//    @Override
//    public void onApplicationEvent(ApplicationEvent event) {
//        Track track1 = new Track(id,name,comment);
//        trackRepository.save(track1);
//    }
//}
//*/
