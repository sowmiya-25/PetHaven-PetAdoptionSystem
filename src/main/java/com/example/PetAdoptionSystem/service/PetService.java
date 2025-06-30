package com.example.PetAdoptionSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PetAdoptionSystem.model.Pet;
import com.example.PetAdoptionSystem.repository.Petrepo;

@Service
public class PetService {
    
      @Autowired
    Petrepo petrepo ;

    public List<Pet>  getallpets(){
        return petrepo.findAll();
    }

    public Pet addnewpet(Pet pet){
        
        return petrepo.save(pet);
    }

     public Pet getpetbyId( int id){
        return petrepo.findById(id).orElseThrow(()-> new RuntimeException("Pet not found with is "+id));
     }

     public Pet updatePet(Pet updatedPet , int id ){
        Pet pet = petrepo.findById(id).orElseThrow(()-> new RuntimeException("Pet not found with is "+id));
        pet.setAge(updatedPet.getAge());
        pet.setName(updatedPet.getName());
        pet.setBreed(updatedPet.getBreed());
        pet.setDescription(updatedPet.getDescription());
        pet.setStatus(updatedPet.getStatus());
        pet.setImageUrl(updatedPet.getImageUrl());
        pet.setType(updatedPet.getType());
        return petrepo.save(pet);

        
     }

     public  void deletepet(int id ){
        petrepo.deleteById(id);
     }
}
