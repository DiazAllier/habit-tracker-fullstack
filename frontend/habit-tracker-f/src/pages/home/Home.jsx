import { useEffect, useState } from "react";
import NavBar from "../navbar/NavBar.jsx";
import RecentActivity from "./RecentActivity.jsx";
import SRAButton from "./SRAButton.jsx";
import TodayWorkoutCard from "./TodayWorkoutCard.jsx";
import WeekTracker from "./weekTracker/WeekTracker.jsx";
import { getUserActiveSession, getUserSession, getWeeklyCalories } from "../../api/sessionWorkoutApi.js";
import axios from "axios";
import { getExercises } from "../../api/exerciseApi.js";
import { getWorkouts } from "../../api/workoutApi.js";

const Home = () => {

  const [session, setSession] = useState(null);
  const [exercises, setExercises] = useState(null);
  const [allSessions, setAllSessions] = useState([]);
  const [calories, setCalories] = useState(0);
  const [minutes, setMinutes] = useState(0);
  const [seconds, setSeconds] = useState(0);
  const [showLongRun, setShowLongRun] = useState(false);

  const userId = 1;
  const workoutId = session?.workout?.id ?? 1;
  const [selectedWorkoutId, setSelectedWorkoutId] = useState(null);

  useEffect(() => {
    fetchAll();
  }, []);

  const fetchAll = async () => {
    try {
      const exercisesData = await getWorkouts();
      setExercises(exercisesData);
    } catch (error) {
      console.error("Failed to fetch exercises:", error);
      setExercises([]);
    }

    try {
      const data = await getUserActiveSession(userId);
      setSession(data);
    } catch (error) {
      console.error("No active session found:");
    }

    try {
      const dataSession = await getUserSession(userId);
      setAllSessions(dataSession);
      computeDuration(dataSession);
    } catch (error) {
      console.error("Failed to fetch all sessions:", error);
      setAllSessions([]);
    }

    try {
      const kcal = await getWeeklyCalories(userId);
      setCalories(kcal);
    } catch (error) {
      console.error("Failed to fetch weekly calories:", error);
      setCalories(0);
    }

  };

  const computeDuration = (data) => {
    const totalDuration = data?.map((session) => session.workout?.exercises).flat().reduce((total, ex) => {
      return total + (ex.duration || 0);
    }, 0) ?? 0;
    setMinutes(Math.floor(totalDuration / 60));
    setSeconds(totalDuration % 60);
  };

  const handleSessionUpdate = async (updatedSession) => {
    setSession(updatedSession);
    computeDuration(updatedSession);
    await fetchAll();
  };

  const handleRunFinish = async () => {
    setShowLongRun(false);
    await fetchAll();
  };

  const formatDuration = () => (
    <>
      <span className="text-4xl font-bold">{minutes}</span>
      <span className="text-sm opacity-80"> min </span>
      <span className="text-4xl font-bold">{seconds}</span>
      <span className="text-sm opacity-80"> sec </span>
    </>
  );

  return (
    <div className="m-5 p-4 space-y-4 bg-gradient-to-b from-[#16120d] to-[#242529] shadow-xl shadow-black-500">

      <NavBar />

      <TodayWorkoutCard
        exercises={exercises}
        data={session}
        onSessionUpdate={handleSessionUpdate}
        onWorkoutSelect={(id) => setSelectedWorkoutId(id)}
      />

      <SRAButton
        userId={userId}
        session={session}
        onSessionUpdate={handleSessionUpdate}
        workoutId={selectedWorkoutId}
        onLongRun={() => setShowLongRun(true)}
      />

      <WeekTracker
        data={allSessions}
        formatDuration={formatDuration}
        calories={calories}
      />

      <RecentActivity data={allSessions} />

      {showLongRun && (
        <LongRunScreen
          userId={userId}
          onFinish={handleRunFinish}
          onCancel={() => setShowLongRun(false)}
        />
      )}

    </div>
  );
};

export default Home;