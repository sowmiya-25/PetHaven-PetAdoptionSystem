package com.example.PetAdoptionSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.example.PetAdoptionSystem.model.AdoptionRequest;

public interface AdoptionRequestRepo extends JpaRepository<AdoptionRequest, Integer> {
    List<AdoptionRequest> findByUserEmail(String email);
}
