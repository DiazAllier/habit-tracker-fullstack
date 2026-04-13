import axios from 'axios';

const API = axios.create({
  baseURL: import.meta.env.VITE_API_URL_AUTH+"/exercises"
});

export const getExercises = async (token) => {
  const res = await fetch(API, {
    headers: {
      "Authorization": `Bearer ${token}`
    }
  });

  return res.json();
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