package com.morrislab.httpconcepts.service.user;

import com.morrislab.httpconcepts.model.User;
import com.morrislab.httpconcepts.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user, long id) {

       User existingUser= userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("The user does not exist"));
       existingUser.setFirstName(user.getFirstName());
       existingUser.setLastName(user.getLastName());
       existingUser.setEmail(user.getEmail());
       existingUser.setLocation(user.getLocation());

       return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(long id) {

        userRepository.deleteById(id);
    }
}
