package com.example.PetAdoptionSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PetAdoptionSystem.model.User;

@Repository
public interface  Userrepo  extends JpaRepository<User, Integer> {

}
