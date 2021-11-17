package com.underworld.apispring.controllers;

import com.underworld.apispring.models.User;
import com.underworld.apispring.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API SPRING BOOT")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user")
    @ApiOperation(value = "Return ALL Users")
    public List<User> userListAll() {
        return userRepository.findAll();
    }

    @GetMapping("user/{id}")
    @ApiOperation(value = "Return One User")
    public User userListById(@PathVariable(value = "id") long id) {
        return userRepository.findById(id);
    }

    @PostMapping("/user")
    @ApiOperation(value = "Insert User")
    public User newUser(@RequestBody User newUser) {
        return  userRepository.save(newUser);
    }

    @PutMapping("/user/{id}")
    @ApiOperation(value = "Update User")
    public User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUserName(newUser.getUserName());
                    user.setEmail(newUser.getEmail());
                    user.setPassword(newUser.getPassword());
                    return userRepository.save(user);
                })
                .orElseGet(() ->{
                    newUser.setId(id);
                    return  userRepository.save(newUser);
                });
    }

    @DeleteMapping("/user/{id}")
    @ApiOperation(value = "DELETE User")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
