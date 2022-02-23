package com.morrislab.httpconcepts.controllers;

import com.morrislab.httpconcepts.model.User;
import com.morrislab.httpconcepts.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

   private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> showAllsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("user/{id}")
    public ResponseEntity<User> showSingleUser(@PathVariable(value = "id") Long id){
        Optional<User> user= Optional.of(userService.findUserById(id));
        return ResponseEntity.ok(user
                .orElseThrow( ()-> new EntityNotFoundException("User Not Found")));
    }

    @DeleteMapping("user/delete/{id}")
    public void deleteUser(@PathVariable(value = "id") Long id){
        userService.deleteUser(id);
    }

    @PostMapping("user/add")
    public User addUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PutMapping("/user/update/{id}")
    public User updateUser(@RequestBody User user, @PathVariable(value = "id") Long id){
        return userService.updateUser(user,id);
    }

}
