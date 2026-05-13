import axios from 'axios';

const API = axios.create({
  baseURL: import.meta.env.VITE_API_URL_CONTROLLER + "/sessions"
});

API.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");
  if (token) config.headers.Authorization = `Bearer ${token}`;
  return config;
});

export const getUserSession = async (userId) => {
  const res = await API.get(`/user/${userId}`);
  return res.data;
};

export const getUserActiveSession = async (userId) => {
  const res = await API.get(`/active/${userId}`);
  return res.data;
};

export const startWorkout = async (userId, workoutId, sessionType = "STRENGTH") => {
  const res = await API.post(`/start/${userId}/${workoutId}?sessionType=${sessionType}`);
  return res.data;
};

export const startRun = async (userId) => {
  const res = await API.post(`/start/run/${userId}`);
  return res.data;
};

export const finishRun = async (sessionId, distanceKm, durationSeconds) => {
  const res = await API.put(
    `/${sessionId}/finish-run?distanceKm=${distanceKm}&durationSeconds=${durationSeconds}`
  );
  return res.data;
};

export const updateProgress = async (sessionId, progress) => {
  const res = await API.put(`/${sessionId}/progress?progress=${progress}`);
  return res.data;
};

export const completeWorkout = async (sessionId, caloriesBurned) => {
  const url = caloriesBurned !== undefined
    ? `/${sessionId}/complete?caloriesBurned=${caloriesBurned}`
    : `/${sessionId}/complete`;
  const res = await API.put(url);
  return res.data;
};

export const getWeeklyCalories = async (userId) => {
  const res = await API.get(`/calories/week/${userId}`);
  return res.data;
};