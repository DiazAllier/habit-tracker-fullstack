import axios from 'axios';

const API = axios.create({
  baseURL: import.meta.env.VITE_API_URL_AUTH
});


export const login = async (email, password) => {
  const res = await fetch(`${API}/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ email, password })
  });

  return res.text(); 
};

export const register = async (email, password) => {
  const res = await fetch(`${API}/register`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ email, password })
  });

  return res.text();
};