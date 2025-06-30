package com.example.PetAdoptionSystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.PetAdoptionSystem.model.AdoptionRequest;
import com.example.PetAdoptionSystem.repository.AdoptionRequestRepo;

@Service
public class AdoptionRequestService {

AdoptionRequestRepo adoptionRequestrepo;

public List<AdoptionRequest> getAllAdoptionRequests(){
    return adoptionRequestrepo.findAll() ;
    
}
public AdoptionRequest getAdoptionRequestById(int id) {
       return adoptionRequestrepo.findById(id).orElseThrow(()-> new RuntimeException("AdoptionRequest not found with id:"+id));
}

    public AdoptionRequest createAdoptionRequest(AdoptionRequest request) {
        return adoptionRequestrepo.save(request);

    }
public void deleteAdoptionRequest(int id) {
        adoptionRequestrepo.deleteById(id);

    }



}
