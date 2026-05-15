import { useEffect, useState } from "react";
import { calculateSessionCalories } from "../utils/calorieUtils";

const LiveTimer = ({ session }) => {

    const [elapsed, setElapsed] = useState(0);
    const [caloriesBurned, setCaloriesBurned] = useState(0);

    useEffect(() => {
        if (!session?.startedAt) return;

        const updateTimer = () => {
            const now = new Date();
            const start = new Date(session.startedAt);
            const seconds = Math.max(0, Math.floor((now - start) / 1000));
            setElapsed(seconds);
            setCaloriesBurned(calculateSessionCalories(session, seconds));
        };

        updateTimer();
        const interval = setInterval(updateTimer, 1000);
        return () => clearInterval(interval);
    }, [session]);

    if (!session) return null;

    const minutes = Math.floor(elapsed / 60).toString().padStart(2, "0");
    const seconds = (elapsed % 60).toString().padStart(2, "0");

    return (
        <div className="live-timer text-sm font-medium">
            <h2 className="text-sm opacity-80">Current Session</h2>
            <h2 className="text-base font-semibold"><strong>{session.workout?.name}</strong></h2>
            <p className="text-white">Timer: <span className="text-gray-300">{minutes}:{seconds}</span></p>
            <p className="text-white">Calories: <span className="text-gray-300">{caloriesBurned} kcal</span></p>
        </div>
    )
};

export default LiveTimer;