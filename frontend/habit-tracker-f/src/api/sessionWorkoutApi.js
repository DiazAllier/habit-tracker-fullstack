import axios from 'axios';

const API = axios.create({
  baseURL: import.meta.env.VITE_API_URL_CONTROLLER +"/sessions"
});

export const getUserSession = async (id) => {
  const res = await API.get(`/active/${id}`);
  return res.data;
};


export const startWorkout = async (userId,workoutId, token) => {
  const res = await fetch(API+`/start/${userId}/${workoutId}`, {
    method: "POST",
    headers: {
      "Authorization": `Bearer ${token}`,
      "Content-Type": "application/json"
    },
    body: JSON.stringify(data)
  });

  return res.json();
};

export const updateProgress = async (sessionId,progress, token) => {
  const res = await fetch(API+`/sessions/${sessionId}/progress?progress=${progress}`, {
    method: "PUT",
    headers: {
      "Authorization": `Bearer ${token}`,
      "Content-Type": "application/json"
    },
    body: JSON.stringify(data)
  });

  return res.json();
};