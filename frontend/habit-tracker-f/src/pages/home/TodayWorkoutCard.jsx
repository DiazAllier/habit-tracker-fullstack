import { useEffect, useState } from "react";
import { getUserSession } from "../../api/sessionWorkoutApi";
import { useWorkoutSession } from "../../hooks/useWorkoutSession";

const TodayWorkoutCard = ({ data }) => {

  return (
    <div>
      <article className="w-full rounded-2xl  bg-gradient-to-b from-[#16120d] to-[#242529]  border-t border-white/10   border-x border-b border-black/50  p-3 text-white shadow-2xl">

        <div className="flex items-center justify-between mb-1 ">
          <h2 className="text-lg font-semibold flex items-center gap-2">
            <span className="text-orange-500">🔥</span> Today's Workout
          </h2>
        </div>

        <hr className="border-1 border-gray-500 my-3"></hr>

        {data ? (
          <>
            <p className="text-xl font-bold mb-6 text-left">{data?.workout.name}</p>

            <div className="flex items-center justify-between mb-1">
              <span className="text-sm text-gray-400 font-medium">{data?.workout.name}</span>
            </div>

            <div className="flex flex-col md:flex-row gap-4 items-center">
              <span className="whitespace-nowrap text-sm font-medium text-gray-300">
                Progress: <span className="text-white">{data?.progress}%</span>
              </span>

              <div className="h-3 w-full rounded-full bg-black/40 shadow-inner">
                <div className="h-full rounded-full bg-[#5DA331] shadow-[0_0_10px_rgba(93,163,49,0.3)]" style={{ width: `${data?.progress}%` }}></div>
              </div>

              <button className="flex items-center gap-2 rounded-full bg-[#5DA331] px-6 py-3 text-sm font-bold text-white shadow-lg transition-transform hover:brightness-110 active:scale-95">
                Continue
              </button>
            </div>
          </>
        ) : (
          <div>
            <p>No workout found</p>
          </div>
        )}
      </article>
    </div>

  );
};
export default TodayWorkoutCard;