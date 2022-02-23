package com.morrislab.httpconcepts.Bootstrap;

import com.morrislab.httpconcepts.model.Location;
import com.morrislab.httpconcepts.model.User;
import com.morrislab.httpconcepts.repository.LocationRepository;
import com.morrislab.httpconcepts.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleData implements CommandLineRunner {

    private LocationRepository locationRepository;
    private UserRepository userRepository;

    public SampleData(LocationRepository locationRepository, UserRepository userRepository) {
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Location nairobi=Location.builder()
                .place("Nairobi")
                .description("This is the Capital City of Kenya")
                .latitude(34.78991)
                .longitude(0.8981)
                .build();

        locationRepository.save(nairobi);


        Location mombasa=Location.builder()
                .place("Mombasa")
                .description("This is at the Coastline of Kenya")
                .latitude(78.091)
                .longitude(0.7832)
                .build();

        locationRepository.save(mombasa);


        Location kampala=Location.builder()
                .place("Kampala")
                .description("This is the Capital City of Uganda")
                .latitude(78.090)
                .longitude(0.78272)
                .build();

        locationRepository.save(kampala);

        User morris= User.builder()
                .firstName("Morris")
                .lastName("Kioko")
                .email("morris@lab.com")
                .location(nairobi)
                .build();

        userRepository.save(morris);

        User purity= User.builder()
                .firstName("Purity")
                .lastName("Gwaro")
                .email("purity@lab.com")
                .location(kampala)
                .build();

        userRepository.save(purity);

        User mary= User.builder()
                .firstName("Mary")
                .lastName("Njoroge")
                .email("mary@lab.com")
                .location(mombasa)
                .build();

        userRepository.save(mary);

    }
}
