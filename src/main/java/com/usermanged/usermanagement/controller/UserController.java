package com.usermanged.usermanagement.controller;

import com.usermanged.usermanagement.jwtservice.JwtService;
import com.usermanged.usermanagement.model.AuthRequest;
import com.usermanged.usermanagement.model.User;
import com.usermanged.usermanagement.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userserviceapi")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

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
        if(userService.getUserById(user.getId()) != null){
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

    @GetMapping("/{userId}")
    public ResponseEntity<User>getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/authToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }

    @GetMapping("/user/userProfile")
    public String welcome() {
        return "Welcome to User Profile";
    }
}
