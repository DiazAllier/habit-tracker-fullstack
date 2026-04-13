import { useEffect, useState } from "react";
import { getWorkouts } from "../api/workoutApi";
import { useAuth } from "../hooks/useAuth";

const Dashboard = () => {
  const { token } = useAuth();
  const [workouts, setWorkouts] = useState([]);

  useEffect(() => {
    if (!token) return;

    const fetchWorkouts = async () => {
      try {
        const data = await getWorkouts(token);
        setWorkouts(data);
      } catch (err) {
        console.error(err);
      }
    };

    fetchWorkouts();
  }, [token]);

  return (
    <div>
      <h1>My Workouts</h1>
      {workouts.length === 0 ? (
        <p>No workouts yet</p>
      ) : (
        <ul>
          {workouts.map((w) => (
            <li key={w.id}>
              {w.date} - {w.exercise} - {w.type} - {w.reps || w.duration || w.distance}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default Dashboard;