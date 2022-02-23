package com.morrislab.httpconcepts.service.dto;

import com.morrislab.httpconcepts.dto.UserLocationDTO;
import com.morrislab.httpconcepts.model.User;
import com.morrislab.httpconcepts.repository.LocationRepository;
import com.morrislab.httpconcepts.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DTOServiceImpl implements DTOService {

    private UserRepository userRepository;
    private LocationRepository locationRepository;

    public DTOServiceImpl(UserRepository userRepository, LocationRepository locationRepository) {
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<UserLocationDTO> getAllUserLocation() {
        return userRepository.findAll()
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserLocationDTO convertEntityToDTO( User user) {

        UserLocationDTO userLocationDTO= UserLocationDTO.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .place(user.getLocation().getPlace())
                .longitude(user.getLocation().getLongitude())
                .latitude(user.getLocation().getLatitude())
                .build();


        return userLocationDTO;



    }

    @Override
    public List<UserLocationDTO> getSpeficUserDTO(long id) {
        return userRepository.findById(id)
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }
}
