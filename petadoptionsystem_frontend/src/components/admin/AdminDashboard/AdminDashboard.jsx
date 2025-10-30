import React, { useEffect, useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { getAllAdoptionRequests, updateAdoptionRequestStatus } from '../../../services/adoptionServices';
import { getAllPets } from '../../../services/petservices';
import './AdminDashboard.css';

function AdminDashboard() {
  const [requests, setRequests] = useState([]);
  const [pets, setPets] = useState([]);
  const navigate = useNavigate();

  // ✅ Role Check when component mounts
  useEffect(() => {
    const role = localStorage.getItem("role");
    if (role !== "ADMIN") {
      alert("Access Denied. Only Admins can view this page.");
      navigate("/");
    }
  }, [navigate]);

  useEffect(() => {
    fetchRequests();
    fetchPets();
  }, []);

  const fetchRequests = () => {
    getAllAdoptionRequests()
      .then((res) => setRequests(res.data))
      .catch((err) => console.error("Error fetching requests", err));
  };

  const fetchPets = () => {
    getAllPets()
      .then((res) => setPets(res.data))
      .catch((err) => console.error("Error fetching pets", err));
  };

  const handleApprove = (id) => {
    updateAdoptionRequestStatus(id, { status: 'Approved' })
      .then(() => {
        alert("Request Approved!");
        fetchRequests();
        fetchPets();
      })
      .catch((err) => console.error("Error approving request", err));
  };

  const handleReject = (id) => {
    updateAdoptionRequestStatus(id, { status: 'Rejected' })
      .then(() => {
        alert("Request Rejected.");
        fetchRequests();
      })
      .catch((err) => console.error("Error rejecting request", err));
  };

  return (
    <div className="admin-dashboard">
      <h2>📋 Adoption Requests</h2>
      {requests.length > 0 ? (
        <table className="admin-table">
          <thead>
            <tr>
              <th>Pet Name</th>
              <th>User</th>
              <th>Status</th>
              <th>Request Date</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {requests.map((r, index) => (
              <tr key={index}>
                <td>{r.pet?.name}</td>
                <td>{r.user?.name}</td>
                <td>{r.status}</td>
                <td>{r.requestDate}</td>
                <td>
                  {r.status === "Pending" ? (
                    <>
                      <button className="approve-btn" onClick={() => handleApprove(r.id)}>Approve</button>
                      <button className="reject-btn" onClick={() => handleReject(r.id)}>Reject</button>
                    </>
                  ) : (
                    <span>{r.status}</span>
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No adoption requests yet.</p>
      )}

      <div className="admin-actions">
        <Link to="/add-pet">
          <button className="add-pet-btn">+ Add New Pet</button>
        </Link>
      </div>

      <h2>🐶 All Pets</h2>
      <div className="pet-list">
        {pets.length > 0 ? (
          pets.map((pet) => (
            <div key={pet.id} className="pet-card">
              <img src={pet.imageUrl} alt={pet.name} />
              <h4>{pet.name}</h4>
              <p>Status: {pet.status}</p>
            </div>
          ))
        ) : (
          <p>No pets available.</p>
        )}
      </div>
    </div>
  );
}

export default AdminDashboard;
