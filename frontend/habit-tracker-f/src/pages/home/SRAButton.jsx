import { completeWorkout, startWorkout } from "../../api/sessionWorkoutApi";

const SRAButton = ({ userId, workoutId, session, onSessionUpdate, onLongRun, onAddExercise }) => {

  const handleStartWorkout = async () => {
    if (!userId || !workoutId) {
      alert("No workout selected to start.");
      return;
    }

    try {
      const newSession = await startWorkout(userId, workoutId);
      onSessionUpdate(newSession);
    } catch (err) {
      console.error("Failed to start workout:", err);
      alert("Could not start workout. Try again.");
    }
  };
  const handleStopWorkout = async () => {
    if (!userId) return;

    try {
      await completeWorkout(userId);
    } catch (err) {
      console.error(err);
    }
  };

  return (
   <div className="flex gap-2 justify-around flex-col sm:flex-row">

    {!session && (
      <button
        onClick={handleStartWorkout}
        className="flex flex-col sm:flex-row items-center gap-2 whitespace-nowrap rounded-full bg-gradient-to-b from-[#242528] to-[#13120d] px-6 py-3 text-sm font-bold text-white shadow-lg transition-transform hover:brightness-110 active:scale-95"
      >
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor" className="size-10">
          <path strokeLinecap="round" strokeLinejoin="round" d="M15.91 11.672a.375.375 0 0 1 0 .656l-5.603 3.113a.375.375 0 0 1-.557-.328V8.887c0-.286.307-.466.557-.327l5.603 3.112Z" />
          <path strokeLinecap="round" strokeLinejoin="round" d="M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z" />
        </svg>
        <span>Start Workout</span>
      </button>
    )}

    {session && !session.completed && (
      <button
        onClick={handleStopWorkout}
        className="flex flex-col sm:flex-row items-center gap-2 whitespace-nowrap rounded-full bg-gradient-to-b from-[#242528] to-[#13120d] px-6 py-3 text-sm font-bold text-white shadow-lg transition-transform hover:brightness-110 active:scale-95"
      >
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor" className="size-10">
          <path strokeLinecap="round" strokeLinejoin="round" d="m9.75 9.75 4.5 4.5m0-4.5-4.5 4.5M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z" />
        </svg>
        <span>Stop Workout</span>
      </button>
    )}


      <button
        onClick={onLongRun}
        className="flex flex-col sm:flex-row items-center gap-2 whitespace-nowrap rounded-full bg-gradient-to-b from-[#242528] to-[#13120d] px-6 py-3 text-sm font-bold text-white shadow-lg transition-transform hover:brightness-110 active:scale-95"
      >
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor" className="size-10">
          <path strokeLinecap="round" strokeLinejoin="round" d="M17.982 18.725A7.488 7.488 0 0 0 12 15.75a7.488 7.488 0 0 0-5.982 2.975m11.963 0a9 9 0 1 0-11.963 0m11.963 0A8.966 8.966 0 0 1 12 21a8.966 8.966 0 0 1-5.982-2.275M15 9.75a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z" />
        </svg>
        <span>Long Run</span>
      </button>
    </div>
  );
};

export default SRAButton;