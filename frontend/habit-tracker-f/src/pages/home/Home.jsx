import { useEffect, useState } from "react";
import NavBar from "../navbar/NavBar.jsx";
import RecentActivity from "./RecentActivity.jsx";
import SRAButton from "./SRAButton.jsx";
import TodayWorkoutCard from "./TodayWorkoutCard.jsx";
import WeekTracker from "./weekTracker/WeekTracker.jsx";
import { getUserSession } from "../../api/sessionWorkoutApi.js";

const Home = () => {

  const [session, setSession] = useState(null);
  const [minutes, setMinutes] = useState(null);
  const [seconds, setSeconds] = useState(null);

  useEffect(() => {
    const fetchSession = async () => {
      try {
        const data = await getUserSession(1);
        setSession(data)
        const totalDuration = data?.workout?.exercises?.reduce((total, exercise) => {
          return total + (exercise.duration || 0);
        }, 0);
        setMinutes(Math.floor(totalDuration / 60));
        setSeconds(totalDuration % 60);
      } catch (err) {
        console.error(err);
      }
    };
    fetchSession();
  }, []);

  const formatDuration = () => {
    return (
      <>
        <span className="text-4xl font-bold">{minutes}</span>
        <span className="text-sm opacity-80">  {" "}min </span>
        <span className="text-4xl font-bold"> {seconds} </span>
        <span className="text-sm opacity-80">  {" "}sec </span>
      </>
    );
  };

  return (
    <div className="m-5 p-4 space-y-4 bg-gradient-to-b from-[#16120d] to-[#242529] shadow-xl shadow-black-500">

      <NavBar />
      <TodayWorkoutCard data={session} />
      <SRAButton />
      <WeekTracker data={session} formatDuration={formatDuration} />
      <RecentActivity data={session} />

    </div>
  );
};

export default Home;