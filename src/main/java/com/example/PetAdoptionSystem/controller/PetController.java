package com.example.PetAdoptionSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.PetAdoptionSystem.model.Pet;
import com.example.PetAdoptionSystem.service.PetService;

@RestController
public class PetController {
    @Autowired
   PetService petservice;

    @GetMapping("/pets")
    public List<Pet> getallpets(){
        return petservice.getallpets();
    }

    @PostMapping("/addpet")
    public String addnewpet(@RequestBody Pet pet )
    {
             petservice.addnewpet(pet);
             
      return "pet added sucessfully";

    }
    @GetMapping("/pets/{id}")
        public Pet getpetbyId(@PathVariable("id") int id){

             return petservice.getpetbyId(id);

        }
    @PutMapping("/pets/{id}")
     public Pet  updatepet( @RequestBody Pet updatedPet ,@PathVariable("id") int id){
                
        return petservice.updatePet(updatedPet, id);
     }

     @DeleteMapping("/delpets/{id}")
     public String  deletepet(@PathVariable("id")int id){
         petservice.deletepet(id);
         return  "pet :"+id+"  is deleted sucessfully";
     }


}
