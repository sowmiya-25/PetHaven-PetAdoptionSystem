import React, { useEffect, useState } from 'react';
import { getAllPets } from '../../services/petservices';
import { requestAdoption } from '../../services/adoptionServices';
import './Home.css';
import { useNavigate } from 'react-router-dom';

function Home() {
  const [pets, setPets] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    getAllPets()
      .then((res) => {
        setPets(res.data);
      })
      .catch((err) => {
        console.error("Error fetching pets: ", err);
      });
  }, []);

  const handleAdopt = (petId) => {
    const userEmail = localStorage.getItem("email");
    if (!userEmail) {
      alert("Please Login first!");
      return;
    }

    requestAdoption(petId, userEmail)
      .then((res) => {
        alert(res.data);
        navigate("/profile");
      })
      .catch((err) => {
        alert(err.response?.data || "Error adopting pet");
        console.error(err);
      });
  };

  return (
    <div className="home-page">
      <div className="hero-section">
        <h1 className="home-head">Adopt a Pet Today</h1>
        <p>Find your next furry friend and give them a second chance at life ❤️</p>
      </div>

      <div className="pet-list">
        {pets.length > 0 ? (
          pets.map((pet) => (
            <div key={pet.id} className="pet-card">
              <img src={pet.imageUrl} alt={pet.name} className="pet-image" />
              <div className="pet-details">
                <h3>{pet.name}</h3>
                <p><strong>Breed:</strong> {pet.breed}</p>
                <p><strong>Age:</strong> {pet.age} years</p>
                <p><strong>Status:</strong> {pet.status}</p>
                {pet.status === "Available" ? (
                  <button className="adopt-btn" onClick={() => handleAdopt(pet.id)}>Adopt</button>
                ) : (
                  <button className="adopt-btn disabled" disabled>Already Adopted</button>
                )}
              </div>
            </div>
          ))
        ) : (
          <p>No pets available right now.</p>
        )}
      </div>
    </div>
  );
}

export default Home;
