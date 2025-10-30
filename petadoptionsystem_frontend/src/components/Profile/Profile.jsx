import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { getUserByEmail } from '../../services/userServices';
import { getAdoptionRequestsByUser } from '../../services/adoptionServices';
import './Profile.css';

function Profile() {
  const navigate = useNavigate();
  const userEmail = localStorage.getItem("email");

  const [user, setUser] = useState({});
  const [adoptions, setAdoptions] = useState([]);

  useEffect(() => {
    if (!userEmail) {
      alert("Please login first!");
      navigate("/login");
    } else {
      getUserByEmail(userEmail)
        .then((res) => setUser(res.data))
        .catch((err) => console.error("User fetch error:", err));

      getAdoptionRequestsByUser(userEmail)
        .then((res) => setAdoptions(res.data))
        .catch((err) => console.error("Adoption fetch error:", err));
    }
  }, [userEmail, navigate]);

  return (
    <div className="profile-page">
      <h2>👤 Your Profile</h2>

      <div className="profile-card">
        <p><strong>Name:</strong> {user.name}</p>
        <p><strong>Email:</strong> {user.email}</p>
        <p><strong>Contact:</strong> {user.contact}</p>
        <p><strong>Role:</strong> {user.role}</p>
      </div>

      <h3>📋 Adoption Requests</h3>
      {adoptions.length > 0 ? (
        <table className="adoption-table">
          <thead>
            <tr>
              <th>Pet Name</th>
              <th>Status</th>
              <th>Request Date</th>
            </tr>
          </thead>
          <tbody>
            {adoptions.map((a, index) => (
              <tr key={index}>
                <td>{a.petName}</td>
                <td>{a.status}</td>
                <td>{a.requestDate}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No adoption requests yet.</p>
      )}

      <h3>🐶 My Adopted Pets</h3>
      <div className="adopted-pets">
        {adoptions.filter(a => a.status === 'Approved').length > 0 ? (
          adoptions
            .filter(a => a.status === 'Approved')
            .map((a, index) => (
              <div key={index} className="adopted-pet-card">
                <img src={a.petImageUrl} alt={a.petName} />
                <p>{a.petName}</p>
              </div>
            ))
        ) : (
          <p>No adopted pets yet.</p>
        )}
      </div>
    </div>
  );
}

export default Profile;
