import axios from 'axios';

const API = axios.create({
  baseURL: import.meta.env.VITE_API_URL_CONTROLLER+"/exercises"
});

API.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");
  if (token) config.headers.Authorization = `Bearer ${token}`;
  return config;
});

export const getExercises = async () => {
  const res = await API.get();
  return res.data;
};

export const addWorkout = async (data, token) => {
  const res = await fetch(API, {
    method: "POST",
    headers: {
      "Authorization": `Bearer ${token}`,
      "Content-Type": "application/json"
    },
    body: JSON.stringify(data)
  });

  return res.json();
};