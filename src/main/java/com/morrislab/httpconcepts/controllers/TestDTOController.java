package com.morrislab.httpconcepts.controllers;

import com.morrislab.httpconcepts.dto.UserLocationDTO;
import com.morrislab.httpconcepts.service.dto.DTOService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestDTOController {

    DTOService dtoService;

    public TestDTOController(DTOService dtoService) {
        this.dtoService = dtoService;
    }

    @GetMapping("/get_user_location_dto")
    public ResponseEntity<List<UserLocationDTO>> getAllUserLocationDTO(){

        return  ResponseEntity.ok(dtoService.getAllUserLocation());
    }

    @GetMapping("get_user_location_dto/{id}")
    public ResponseEntity<List<UserLocationDTO>> getUserLocationDTO(@PathVariable(value = "id") Long id){

        return ResponseEntity.ok(dtoService.getSpeficUserDTO(id));
    }
}
