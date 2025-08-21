package com.example.PetAdoptionSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.PetAdoptionSystem.model.AdoptionRequest;
import com.example.PetAdoptionSystem.repository.AdoptionRequestRepo;

@Service
public class AdoptionRequestService {

    @Autowired
    private AdoptionRequestRepo adoptionRequestRepo;

    public List<AdoptionRequest> getAllAdoptionRequests(){ 
            return adoptionRequestRepo.findAll(); 
    }

    public AdoptionRequest getAdoptionRequestById(int id) {
        return adoptionRequestRepo.findById(id).orElseThrow(() -> new RuntimeException("Adoption request not found"));
    }

    public AdoptionRequest createAdoptionRequest(AdoptionRequest request) {
        return adoptionRequestRepo.save(request);
    }
    
    public void deleteAdoptionRequest(int id) {
        adoptionRequestRepo.deleteById(id);
    }
    public List<AdoptionRequest> getRequestsByUserEmail(String email) {
        return adoptionRequestRepo.findByUserEmail(email);
    }
}
