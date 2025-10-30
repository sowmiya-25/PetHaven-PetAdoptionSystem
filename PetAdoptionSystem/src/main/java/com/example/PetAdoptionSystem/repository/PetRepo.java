package com.example.PetAdoptionSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.PetAdoptionSystem.model.Pet;

public interface PetRepo extends JpaRepository<Pet, Integer> {}
