import { useEffect, useState } from "react";
import { updateProgress } from "../../api/sessionWorkoutApi";
import DropDown from "../../reusables/DropDown";
import LiveTimer from "../../reusables/LiveTimer";
const TodayWorkoutCard = ({ data, onSessionUpdate, exercises, onWorkoutSelect }) => {


  useEffect(() => {
  }, []);



  const handleContinue = async () => {
    if (!data?.id) return;
    try {
      const newProgress = Math.min((data.progress || 0) + 20, 100);
      const updated = await updateProgress(data.id, newProgress);
      onSessionUpdate(updated);
    } catch (err) {
      console.error("Failed to update progress:", err);
    }
  };

  return (
    <div>
      <article className="w-full rounded-2xl bg-gradient-to-b from-[#16120d] to-[#242529] border-t border-white/10 border-x border-b border-black/50 p-3 text-white shadow-2xl">

        <div className="flex items-center justify-between mb-1">
          <h2 className="text-lg font-semibold flex items-center gap-2">
            <span className="text-orange-500">🔥</span> Today's Workout
          </h2>
        </div>

        <hr className="border-1 border-gray-500 my-3" />

        {data ? (
          <>
            <div className="flex flex-col md:flex-row gap-4 items-center">
              <span className="whitespace-nowrap text-sm font-medium text-gray-300">
                <span className="text-white">{<LiveTimer session={data} />}</span>
              </span>
            </div>
          </>
        ) : (
          <div className="flex flex-col items-center gap-4 py-2">
            <p className="text-gray-400">No active workout. Start one below!</p>
            <DropDown items={exercises} onSelect={(item) => onWorkoutSelect(item.id)} />
          </div>
        )}
      </article>
    </div>
  );
};

export default TodayWorkoutCard;