package com.morrislab.httpconcepts.service.dto;

import com.morrislab.httpconcepts.dto.UserLocationDTO;
import com.morrislab.httpconcepts.model.User;
import com.morrislab.httpconcepts.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DTOServiceImpl implements DTOService {

    private final UserRepository userRepository;

    public DTOServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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

        return UserLocationDTO.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .place(user.getLocation().getPlace())
                .longitude(user.getLocation().getLongitude())
                .latitude(user.getLocation().getLatitude())
                .build();

    }

    @Override
    public List<UserLocationDTO> getSpecificUserDTO(long id) {
        return userRepository.findById(id)
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    /*
    @Override
    public Page<User> getAllUsersUsingPage(List<User> user) {
        Page <User> allUsers=userRepository.findAllUsersInApage(user, PageRequest.of(0,100));
        return allUsers;
    }
    */

}
