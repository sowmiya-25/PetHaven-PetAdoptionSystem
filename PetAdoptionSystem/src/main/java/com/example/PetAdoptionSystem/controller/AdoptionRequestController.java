package com.example.PetAdoptionSystem.controller;

import com.example.PetAdoptionSystem.model.AdoptionRequest;
import com.example.PetAdoptionSystem.model.Pet;
import com.example.PetAdoptionSystem.model.User;
import com.example.PetAdoptionSystem.service.AdoptionRequestService;
import com.example.PetAdoptionSystem.service.PetService;
import com.example.PetAdoptionSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/adoptions")
public class AdoptionRequestController {

    @Autowired
    private AdoptionRequestService adoptionRequestService;

    @Autowired
    private PetService petService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<AdoptionRequest> getAllAdoptionRequests() {
        return adoptionRequestService.getAllAdoptionRequests();
    }

    @GetMapping("/{id}")
    public AdoptionRequest getAdoptionRequestById(@PathVariable int id) {
        return adoptionRequestService.getAdoptionRequestById(id);
    }

    // ✅ Corrected adopt endpoint
    @PostMapping("/new")
    public ResponseEntity<?> createAdoptionRequestByIds(@RequestParam int petId, @RequestParam String userEmail) {
        Pet pet = petService.getPetById(petId);
        if (pet == null) return ResponseEntity.badRequest().body("Pet not found!");

        if (!"Available".equalsIgnoreCase(pet.getStatus()))
            return ResponseEntity.badRequest().body("Pet already adopted!");

        User user = userService.getUserByEmail(userEmail);
        if (user == null) return ResponseEntity.badRequest().body("User not found!");

        AdoptionRequest request = new AdoptionRequest();
        request.setPet(pet);
        request.setUser(user);
        request.setStatus("Pending");
        request.setRequestDate(LocalDate.now());

        adoptionRequestService.createAdoptionRequest(request);

        return ResponseEntity.ok("Adoption request sent for approval!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAdoptionRequestStatus(
            @PathVariable int id, @RequestBody AdoptionRequest updatedRequest) {

        AdoptionRequest existingRequest = adoptionRequestService.getAdoptionRequestById(id);
        existingRequest.setStatus(updatedRequest.getStatus());
        adoptionRequestService.createAdoptionRequest(existingRequest);

        if ("Approved".equalsIgnoreCase(updatedRequest.getStatus())) {
            Pet pet = existingRequest.getPet();
            pet.setStatus("Adopted");
            petService.updatePet(pet, pet.getId());
        }

        return ResponseEntity.ok("Adoption request updated successfully.");
    }

    @DeleteMapping("/{id}")
    public String deleteAdoptionRequest(@PathVariable int id) {
        adoptionRequestService.deleteAdoptionRequest(id);
        return "Adoption request deleted successfully with id: " + id;
    }

    @GetMapping("/user/requests")
    public List<Map<String, Object>> getRequestsByUser(@RequestParam String email) {
        List<AdoptionRequest> requests = adoptionRequestService.getRequestsByUserEmail(email);
        List<Map<String, Object>> response = new ArrayList<>();

        for (AdoptionRequest req : requests) {
            Map<String, Object> map = new HashMap<>();
            map.put("petName", req.getPet() != null ? req.getPet().getName() : "N/A");
            map.put("petImageUrl", req.getPet() != null ? req.getPet().getImageUrl() : null);
            map.put("requestDate", req.getRequestDate());
            map.put("status", req.getStatus());
            response.add(map);
        }

        return response;
    }
}
