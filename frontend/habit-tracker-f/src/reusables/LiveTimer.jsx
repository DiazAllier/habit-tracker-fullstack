import { useEffect, useState } from "react";

const LiveTimer = ({ session }) => {

    const [elapsed, setElapsed] = useState(0);

    useEffect(() => {
        const interval = setInterval(() => {
            const now = new Date();
            const start = new Date(session.startedAt);
            setElapsed(Math.floor((now - start) / 1000));
        }, 1000);
        return () => clearInterval(interval);
    }, [session]);

    const minutes = Math.floor(elapsed / 60).toString().padStart(2, "0");
    const seconds = (elapsed % 60).toString().padStart(2, "0");


    return (
        <div className="live-timer text-sm font-medium">
            <h2>Current Session</h2>
            <h2><strong>{session.workout?.name}</strong></h2>
            <p className="text-white">Timer: <span className="text-gray-300"> {minutes}:{seconds}</span></p>

        </div>

    )
};

export default LiveTimer;