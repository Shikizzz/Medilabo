package com.medilabo.authorization.controller;

import com.medilabo.authorization.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.medilabo.authorization.model.User;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity getUser(@RequestParam String username){
        User user = userService.getUser(username);
        if(user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(user);
        }
    }

    @PostMapping
    public ResponseEntity postUser(@RequestBody User user){
        if(userService.addUser(user)){
            return ResponseEntity.status(HttpStatus.OK)
                .body("User created successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Error, User already in Database");
        }
    }

    @PutMapping
    public ResponseEntity putUser(@RequestBody User user){
        if(userService.editUser(user)){
            return ResponseEntity.status(HttpStatus.OK)
                    .body("User modified successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Error, User not found in Database");
        }
    }

    @DeleteMapping
    public ResponseEntity deleteUser(@RequestParam String username){
        if(userService.deleteUser(username)){
            return ResponseEntity.status(HttpStatus.OK)
                    .body("User deleted successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Error, User not found in Database");
        }
    }

}
