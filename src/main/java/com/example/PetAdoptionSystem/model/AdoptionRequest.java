package com.example.PetAdoptionSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class AdoptionRequest {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id ;

    private  String requestDate;
    private String status;
    
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="pet_id")
    private Pet pet;

    public AdoptionRequest() {
    }

    public AdoptionRequest(int id, String requestDate, String status, User user, Pet pet) {
        this.id = id;
        this.requestDate = requestDate;
        this.status = status;
        this.user = user;
        this.pet = pet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }



}
