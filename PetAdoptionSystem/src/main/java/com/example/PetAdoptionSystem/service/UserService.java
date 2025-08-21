package com.example.PetAdoptionSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
import com.example.PetAdoptionSystem.model.User;
import com.example.PetAdoptionSystem.model.AdoptionRequest;
import com.example.PetAdoptionSystem.model.Pet;
import com.example.PetAdoptionSystem.repository.UserRepo;
import com.example.PetAdoptionSystem.repository.AdoptionRequestRepo;
import jakarta.annotation.PostConstruct;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AdoptionRequestRepo adoptionRequestRepo;

    @PostConstruct
    public void initAdmin() {
        userRepo.findByEmail("admin@example.com").orElseGet(() -> {
            User admin = new User();
            admin.setName("Admin");
            admin.setEmail("admin@example.com");
            admin.setPassword("admin123");
            admin.setContact("0000000000");
            admin.setRole("ADMIN");
            return userRepo.save(admin);
        });
    }

    public User signup(User user) {
        user.setRole("USER");
        return userRepo.save(user);
    }

    public User login(String email, String password) {
        User user = userRepo.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }
        return user;
    }

    public List<User> getAllUsers() {
         return userRepo.findAll();
    }
    public User getUserById(int id) {
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Map<String, Object> getUserProfileWithPets(int id) {
        // Get user by ID
        User user = getUserById(id);

        // Get all adoption requests made by this user's email
        List<AdoptionRequest> requests = adoptionRequestRepo.findByUserEmail(user.getEmail());

        // Create a list to store adopted pets
        List<Pet> adoptedPets = new ArrayList<>();

        // For each adoption request, get the pet and add to the list
        for (AdoptionRequest request : requests) {
            Pet pet = request.getPet();
            adoptedPets.add(pet);
        }

        // Create a Map to hold the profile response
        Map<String, Object> profile = new HashMap<>();
        profile.put("id", user.getId());
        profile.put("name", user.getName());
        profile.put("email", user.getEmail());
        profile.put("adoptedPets", adoptedPets);

        // Return the profile map
        return profile;
    }


    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
