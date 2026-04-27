import { useEffect, useRef, useState } from "react";
import { startRun, finishRun } from "../../api/sessionWorkoutApi";

const LongRunScreen = ({ userId, onFinish, onCancel }) => {
  const [phase, setPhase] = useState("ready"); // ready | running | done
  const [seconds, setSeconds] = useState(0);
  const [distanceKm, setDistanceKm] = useState(0);
  const [sessionId, setSessionId] = useState(null);
  const [summary, setSummary] = useState(null);
  const intervalRef = useRef(null);

  // Simulate distance: ~10 km/h = 0.00278 km per second
  const SPEED_KM_PER_SEC = 0.00278;

  const handleStart = async () => {
    try {
      const session = await startRun(userId);
      setSessionId(session.id);
      setPhase("running");

      intervalRef.current = setInterval(() => {
        setSeconds((s) => s + 1);
        setDistanceKm((d) => parseFloat((d + SPEED_KM_PER_SEC).toFixed(4)));
      }, 1000);
    } catch (err) {
      alert("Could not start run. You may already have an active session.");
    }
  };

  const handleStop = async () => {
    clearInterval(intervalRef.current);
    try {
      const finalDist = parseFloat(distanceKm.toFixed(2));
      const result = await finishRun(sessionId, finalDist, seconds);
      setSummary(result);
      setPhase("done");
    } catch (err) {
      alert("Failed to save run. Try again.");
    }
  };

  useEffect(() => {
    return () => clearInterval(intervalRef.current); // cleanup on unmount
  }, []);

  const formatTime = (totalSeconds) => {
    const m = Math.floor(totalSeconds / 60).toString().padStart(2, "0");
    const s = (totalSeconds % 60).toString().padStart(2, "0");
    return `${m}:${s}`;
  };

  const calcPace = () => {
    if (distanceKm < 0.01) return "--:--";
    const paceSecPerKm = seconds / distanceKm;
    const pm = Math.floor(paceSecPerKm / 60).toString().padStart(2, "0");
    const ps = Math.floor(paceSecPerKm % 60).toString().padStart(2, "0");
    return `${pm}:${ps}`;
  };

  const calcCalories = () => Math.round(distanceKm * 60);

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/80 backdrop-blur-sm">
      <div className="w-full max-w-md rounded-3xl bg-gradient-to-b from-[#16120d] to-[#242529] border border-white/10 p-8 text-white shadow-2xl">

        {/* Header */}
        <div className="flex items-center justify-between mb-6">
          <h2 className="text-2xl font-bold flex items-center gap-2">
            🏃 Long Run
          </h2>
          {phase === "ready" && (
            <button onClick={onCancel} className="text-gray-400 hover:text-white text-sm">
              ✕ Cancel
            </button>
          )}
        </div>

        {/* READY phase */}
        {phase === "ready" && (
          <div className="text-center space-y-6">
            <p className="text-gray-400">Your run will be simulated at ~10 km/h.</p>
            <div className="text-6xl">🏁</div>
            <button
              onClick={handleStart}
              className="w-full rounded-full bg-[#5DA331] px-6 py-4 text-lg font-bold text-white shadow-lg hover:brightness-110 active:scale-95 transition-transform"
            >
              Start Run
            </button>
          </div>
        )}

        {/* RUNNING phase */}
        {phase === "running" && (
          <div className="space-y-6">
            {/* Timer */}
            <div className="text-center">
              <p className="text-gray-400 text-sm mb-1">Time</p>
              <p className="text-6xl font-bold font-mono">{formatTime(seconds)}</p>
            </div>

            {/* Stats grid */}
            <div className="grid grid-cols-3 gap-3">
              <div className="rounded-2xl bg-black/30 p-4 text-center">
                <p className="text-gray-400 text-xs mb-1">Distance</p>
                <p className="text-2xl font-bold">{distanceKm.toFixed(2)}</p>
                <p className="text-gray-400 text-xs">km</p>
              </div>
              <div className="rounded-2xl bg-black/30 p-4 text-center">
                <p className="text-gray-400 text-xs mb-1">Pace</p>
                <p className="text-2xl font-bold">{calcPace()}</p>
                <p className="text-gray-400 text-xs">min/km</p>
              </div>
              <div className="rounded-2xl bg-orange-500/20 p-4 text-center border border-orange-500/30">
                <p className="text-gray-400 text-xs mb-1">Calories</p>
                <p className="text-2xl font-bold text-orange-400">{calcCalories()}</p>
                <p className="text-gray-400 text-xs">kcal</p>
              </div>
            </div>

            {/* Animated running indicator */}
            <div className="flex items-center justify-center gap-1">
              {[0,1,2,3,4].map(i => (
                <div
                  key={i}
                  className="w-2 rounded-full bg-[#5DA331]"
                  style={{
                    height: `${12 + Math.sin((seconds + i) * 0.8) * 8}px`,
                    transition: "height 0.3s ease"
                  }}
                />
              ))}
            </div>

            <button
              onClick={handleStop}
              className="w-full rounded-full bg-red-600 px-6 py-4 text-lg font-bold text-white shadow-lg hover:brightness-110 active:scale-95 transition-transform"
            >
              ⏹ Stop & Save
            </button>
          </div>
        )}

        {/* DONE phase */}
        {phase === "done" && summary && (
          <div className="space-y-6 text-center">
            <div className="text-5xl">🎉</div>
            <h3 className="text-2xl font-bold text-[#5DA331]">Run Complete!</h3>

            <div className="grid grid-cols-2 gap-3">
              <div className="rounded-2xl bg-black/30 p-4">
                <p className="text-gray-400 text-xs mb-1">Distance</p>
                <p className="text-3xl font-bold">{summary.distanceKm?.toFixed(2)}</p>
                <p className="text-gray-400 text-xs">km</p>
              </div>
              <div className="rounded-2xl bg-black/30 p-4">
                <p className="text-gray-400 text-xs mb-1">Time</p>
                <p className="text-3xl font-bold">{formatTime(summary.durationSeconds)}</p>
              </div>
              <div className="rounded-2xl bg-orange-500/20 p-4 border border-orange-500/30 col-span-2">
                <p className="text-gray-400 text-xs mb-1">Calories Burned</p>
                <p className="text-3xl font-bold text-orange-400">{summary.caloriesBurned} kcal</p>
              </div>
            </div>

            <button
              onClick={onFinish}
              className="w-full rounded-full bg-[#5DA331] px-6 py-4 text-lg font-bold text-white shadow-lg hover:brightness-110 active:scale-95 transition-transform"
            >
              Back to Dashboard
            </button>
          </div>
        )}
      </div>
    </div>
  );
};

export default LongRunScreen;