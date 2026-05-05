import { useEffect, useState } from "react";
import { getUserSession } from "../../api/sessionWorkoutApi";



const RecentActivity = ({ data }) => {

   
    return (
        <div className="flex gap-2 w-full rounded-xl flex-col items-start bg-gradient-to-b from-[#16120d] to-[#242529] p-3 shadow-lg/30 shadow-black-500">
            <h3 className="font-semibold">Recent Activity</h3>
            <hr className="border-1 border-gray-500 w-full"></hr>
            <ul role="list" className="divide-y divide-white/5 w-full flex ">
                <li className="flex gap-x-6 py-2 flex-col w-full ">
                    {data?.workout?.exercises?.map((data, index) => (
                        <div key={index} className={`flex border-b hover:scale-101 border-gray-600 px-4 py-2 text-sm font-medium w-full`}>
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" className="flex items-center gap-2 rounded-full bg-[#5DA331] size-10 text-white shadow-lg transition-transform hover:brightness-110" >
                                <path fillRule="evenodd" d="M2.25 12c0-5.385 4.365-9.75 9.75-9.75s9.75 4.365 9.75 9.75-4.365 9.75-9.75 9.75S2.25 17.385 2.25 12Zm13.36-1.814a.75.75 0 1 0-1.22-.872l-3.236 4.53L9.53 12.22a.75.75 0 0 0-1.06 1.06l2.25 2.25a.75.75 0 0 0 1.14-.094l3.75-5.25Z" clipRule="evenodd" />
                            </svg>
                            <div className="px-3 text-start ">
                                <p className="text-sm/6 font-semibold text-white">{data.exercise.name}</p>
                                <p className="mt-1 truncate text-xs/5 text-gray-400">{data.duration}</p>
                                <p className="mt-1 truncate text-xs/5 text-gray-400">{data.sets} sets</p>
                                <p className="mt-1 truncate text-xs/5 text-gray-400">{data.reps} reps</p>
                            </div>
                        </div>
                    ))}

                </li>
            </ul>
        </div>
    )
}

export default RecentActivity;