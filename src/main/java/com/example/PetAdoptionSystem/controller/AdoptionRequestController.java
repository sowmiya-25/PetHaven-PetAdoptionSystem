package com.example.PetAdoptionSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.PetAdoptionSystem.model.AdoptionRequest;
import com.example.PetAdoptionSystem.service.AdoptionRequestService;

@RestController
public class AdoptionRequestController {
    @Autowired
    AdoptionRequestService  adoptionrequestService;
    
    @PostMapping("/addadoptions")
    public AdoptionRequest createAdoptionRequest(@RequestBody AdoptionRequest request) {
        return adoptionrequestService.createAdoptionRequest(request);
    }

    @GetMapping("/AdoptionRequest")
    public List<AdoptionRequest>  getAllAdoptionRequests(){
            return  adoptionrequestService.getAllAdoptionRequests();
    }
    @GetMapping("/adoptions/{id}")
    public AdoptionRequest getAdoptionRequestById(@PathVariable("id") int id ){
        return adoptionrequestService.getAdoptionRequestById(id);
    }

    @DeleteMapping("/adoptions/{id}")
    public String deleteAdoptionRequest(@PathVariable int id) {
        adoptionrequestService.deleteAdoptionRequest(id);
        return "Adoption Request deleted successfully with id:"+id;
    }
}