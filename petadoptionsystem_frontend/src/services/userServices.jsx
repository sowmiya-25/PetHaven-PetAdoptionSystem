import axios from "axios";

const BASE_URL="http://localhost:8080/users";

export const signupUser = (user) => axios.post(`${BASE_URL}/signup`, user);

export const loginUser = (user) => axios.post(`${BASE_URL}/login`, user);

export const getUserByEmail =(email) =>axios.get(`${BASE_URL}/profile?email=${email}`);

