import axios from 'axios';

const API = axios.create({
  baseURL: import.meta.env.VITE_API_URL_AUTH
});

API.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");
  if (token) config.headers.Authorization = `Bearer ${token}`;
  return config;
});

export const login = async (email, password) => {
  const res = await API.post(`/login`, { email, password });
  return res.data;
};

export const register = async (email, password) => {
  const res = await API.post(`/register`, { email, password });
  return res.data;
};

