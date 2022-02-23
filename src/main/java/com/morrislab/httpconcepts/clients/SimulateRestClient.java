package com.morrislab.httpconcepts.clients;

import com.morrislab.httpconcepts.model.Location;
import com.morrislab.httpconcepts.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class SimulateRestClient {

    private static final String GET_ALL_USERS_API="http://localhost:8080/users";
    private static final String GET_USER_BY_ID_API="http://localhost:8080/user/{id}";
    private static final String DELETE_USER_API="http://localhost:8080/user/delete/{id}";
    private static final String UPDATE_USER_API="http://localhost:8080/user/update/{id}";
    private static final String CREATE_USER_API="http://localhost:8080/user/add";

    //Instantiate the RestTemplate
    static RestTemplate restTemplate =new RestTemplate();

   @GetMapping("/consumeGetAllUserApi")
    public static ResponseEntity<?> callGetAllUsersApi(){
        //create a Http Header
        HttpHeaders headers= new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("custom-header", "foo");

        //Instantiate an HttpEntity
        HttpEntity <String> entity=new HttpEntity<>("parameters",headers);

        //call the RestTemplate
        ResponseEntity<String> result= restTemplate.exchange(GET_ALL_USERS_API, HttpMethod.GET, entity, String.class);

        return result;
    }
    @GetMapping("/consumeGetUserByIdApi/{id}")
    public static User callGetuserByIdApi( @PathVariable Long id){

        //call the RestTemplate
       User user= restTemplate.getForObject(GET_USER_BY_ID_API, User.class, id);

       return user;
    }

    @PostMapping("/consumeCreateUserApi")
    public static  ResponseEntity<User> callAdduserApi(){
       User userRequestBody= User.builder()
               .firstName("Jackson")
               .lastName("Kimeu")
               .email("jackson@lab.com")
               .location(Location.builder()
                       .id(1)
                       .build())
               .build();

       ResponseEntity <User> userResponseEntity = restTemplate.postForEntity(CREATE_USER_API,userRequestBody, User.class);

       return userResponseEntity;
    }

    @PutMapping("consumeUpdateUserApi/{id}")
    public static void callUpdateUserApi(@PathVariable (value = "id") Long id){

       /*
       Map<String, Long> param =new HashMap<>();
       param.put("id", 1L);
       */
       User newUser=User.builder()
               .firstName("John")
               .lastName("Cena")
               .email("john@lab.com")
               .location(Location.builder()
                       .id(3)
                       .build())
               .build();
       restTemplate.put(UPDATE_USER_API, newUser,id);
    }

    @DeleteMapping("consumeDeleteUserApi/{id}")
    public static  void callDeleteApi(@PathVariable (value = "id") Long id){

       restTemplate.delete(DELETE_USER_API,id);
    }
}
