package com.usermanged.usermanagement.controller;

import com.usermanged.usermanagement.model.User;
import com.usermanged.usermanagement.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userserviceapi")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<String>addUser(@RequestBody User user) {
        User saveUser = userService.addUser(user);
        return new ResponseEntity<>("A User successfully created!", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteUser(@PathVariable Long id) {
        if(userService.getUserById(id) != null){
            userService.deleteUser(id);
            return new ResponseEntity<>("A User successfully deleted!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("A User could not be deleted!", HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping
    public ResponseEntity<String>updateUser(@RequestBody User user) {
        if(userService.getUserById(user.getUserId()) != null){
            User saveUser = userService.updateUser(user);
            return new ResponseEntity<>("A User successfully updated!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The User does not exist!", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<User>>getAllUsers() {
        List<User> userList = userService.getAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/findByUserId")
    public ResponseEntity<User>getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
