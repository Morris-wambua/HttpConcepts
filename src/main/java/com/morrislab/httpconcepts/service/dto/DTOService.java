package com.morrislab.httpconcepts.service.dto;

import com.morrislab.httpconcepts.dto.UserLocationDTO;
import com.morrislab.httpconcepts.model.User;

import java.util.List;

public interface DTOService {

    List<UserLocationDTO> getAllUserLocation();

    UserLocationDTO convertEntityToDTO(User user);

    List<UserLocationDTO> getSpecificUserDTO(long id);
}
