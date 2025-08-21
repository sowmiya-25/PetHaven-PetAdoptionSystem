package com.example.PetAdoptionSystem.controller;

import com.example.PetAdoptionSystem.model.User;
import com.example.PetAdoptionSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        return userService.signup(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User loginUser) {
        return userService.login(loginUser.getEmail(), loginUser.getPassword());
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<Map<String, Object>> getUserProfile(@PathVariable int id) {
        Map<String, Object> profile = userService.getUserProfileWithPets(id);
        return ResponseEntity.ok(profile);
    }  

    @GetMapping("/profile")
    public User getProfileByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }
}
