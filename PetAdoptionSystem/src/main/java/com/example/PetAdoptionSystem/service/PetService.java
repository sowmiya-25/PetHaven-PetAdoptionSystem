package com.example.PetAdoptionSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.PetAdoptionSystem.model.Pet;
import com.example.PetAdoptionSystem.repository.PetRepo;

@Service
public class PetService {

    @Autowired
    private PetRepo petRepo;

    public List<Pet> getAllPets() { return petRepo.findAll(); }
    public void addNewPet(Pet pet) { petRepo.save(pet); }
    public Pet getPetById(int id) {
        return petRepo.findById(id).orElseThrow(() -> new RuntimeException("Pet not found"));
    }
    public Pet updatePet(Pet updatedPet, int id) {
        Pet pet = getPetById(id);
        pet.setAge(updatedPet.getAge());
        pet.setName(updatedPet.getName());
        pet.setBreed(updatedPet.getBreed());
        pet.setDescription(updatedPet.getDescription());
        pet.setStatus(updatedPet.getStatus());
        pet.setImageUrl(updatedPet.getImageUrl());
        pet.setType(updatedPet.getType());
        return petRepo.save(pet);
    }
    public void deletePetById(int id) { petRepo.deleteById(id); }
}
