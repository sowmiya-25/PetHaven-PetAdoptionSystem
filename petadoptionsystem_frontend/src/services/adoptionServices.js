import axios from "axios";

const BASE_URL = "http://localhost:8080/adoptions";

// ✅ use the corrected /new endpoint with params
export const requestAdoption = (petId, userEmail) =>
  axios.post(`${BASE_URL}/new`, null, {
    params: { petId, userEmail }
  });

export const getAdoptionRequestsByUser = (email) =>
  axios.get(`${BASE_URL}/user/requests?email=${email}`);

export const getAllAdoptionRequests = () =>
  axios.get("http://localhost:8080/adoptions");

export const updateAdoptionRequestStatus = (id, updatedRequest) =>
  axios.put(`http://localhost:8080/adoptions/${id}`, updatedRequest);
