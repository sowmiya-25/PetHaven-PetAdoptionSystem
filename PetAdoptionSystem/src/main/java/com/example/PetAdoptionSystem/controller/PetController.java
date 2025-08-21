package com.example.PetAdoptionSystem.controller;

import com.example.PetAdoptionSystem.model.AdoptionRequest;
import com.example.PetAdoptionSystem.model.Pet;
import com.example.PetAdoptionSystem.model.User;
import com.example.PetAdoptionSystem.service.AdoptionRequestService;
import com.example.PetAdoptionSystem.service.PetService;
import com.example.PetAdoptionSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private UserService userService;

    @Autowired
    private AdoptionRequestService adoptionRequestService;

    // Get all pets
    @GetMapping
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    // Add new pet
    @PostMapping
    public String addNewPet(@RequestBody Pet pet) {
        petService.addNewPet(pet);
        return "Pet added successfully";
    }

    // Get pet by id
    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable int id) {
        return petService.getPetById(id);
    }

    // Update pet
    @PutMapping("/{id}")
    public Pet updatePet(@PathVariable int id, @RequestBody Pet updatedPet) {
        return petService.updatePet(updatedPet, id);
    }

    // Delete pet
    @DeleteMapping("/{id}")
    public String deletePetById(@PathVariable int id) {
        petService.deletePetById(id);
        return "Pet deleted successfully with id: " + id;
    }

    // ✅ Adopt pet (create adoption request as 'Pending')
    @PostMapping("/adopt/{id}")
    public String adoptPet(@PathVariable int id, @RequestParam String userEmail) {
        Pet pet = petService.getPetById(id);
        if (pet == null) {
            return "Pet not found!";
        }

        if (!"Available".equalsIgnoreCase(pet.getStatus())) {
            return "Pet already adopted!";
        }

        User user = userService.getUserByEmail(userEmail);
        if (user == null) {
            return "User not found!";
        }

        // Don’t mark pet as Adopted yet — wait for admin approval

        // Create new Adoption Request with status 'Pending'
        AdoptionRequest request = new AdoptionRequest();
        request.setPet(pet);
        request.setUser(user);
        request.setStatus("Pending");
        request.setRequestDate(LocalDate.now());

        adoptionRequestService.createAdoptionRequest(request);

        return "Adoption request sent for approval!";
    }
}
