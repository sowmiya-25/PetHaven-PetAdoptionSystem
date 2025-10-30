import React, { useState } from 'react';
import { addNewPet } from '../../../services/petservices';
import './AddPetForm.css';
import { useNavigate } from 'react-router-dom';

function AddPetForm() {
  const [pet, setPet] = useState({
    name: '',
    age: '',
    breed: '',
    description: '',
    type: '',
    imageUrl: '',
    status: 'Available'
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    setPet({ ...pet, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault(); // prevent page reload
    addNewPet(pet)
      .then(() => {
        alert('Pet added successfully!');
        setPet({
          name: '',
          age: '',
          breed: '',
          description: '',
          type: '',
          imageUrl: '',
          status: 'Available'
        });
        navigate('/admin-home'); // optional: redirect to admin home after adding
      })
      .catch((err) => {
        console.error('Error adding pet:', err);
        alert('Failed to add pet.');
      });
  };

  return (
    <div className="addpet-page">
      <div className="addpet-box">
        <h2>Add New Pet</h2>
        <form className="addpet-form" onSubmit={handleSubmit}>
          <input type="text" name="name" placeholder="Pet Name" value={pet.name} onChange={handleChange} required />
          <input type="number" name="age" placeholder="Age" value={pet.age} onChange={handleChange} required />
          <input type="text" name="breed" placeholder="Breed" value={pet.breed} onChange={handleChange} required />
          <textarea name="description" placeholder="Description" value={pet.description} onChange={handleChange} required />
          <input type="text" name="type" placeholder="Type (Dog/Cat...)" value={pet.type} onChange={handleChange} required />
          <input type="text" name="imageUrl" placeholder="Image URL" value={pet.imageUrl} onChange={handleChange} required />
          <button type="submit">Add Pet</button>
        </form>
      </div>
    </div>
  );
}

export default AddPetForm;
