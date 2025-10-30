import axios from "axios";

const BASE_URL = "http://localhost:8080";

export const getAllPets = () => axios.get(`${BASE_URL}/pets`);

export const addNewPet = (pet) =>
  axios.post('http://localhost:8080/pets', pet);
