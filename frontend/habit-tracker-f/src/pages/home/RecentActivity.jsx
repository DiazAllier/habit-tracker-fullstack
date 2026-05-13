import { calculateSessionCalories, getWorkoutPlannedSeconds } from "../../utils/calorieUtils";

const RecentActivity = ({ data }) => {
    if (!data?.length) {
        return (
            <div className="flex gap-2 w-full rounded-xl flex-col items-start bg-gradient-to-b from-[#16120d] to-[#242529] p-3 shadow-lg/30 shadow-black-500">
                <h3 className="font-semibold">Recent Activity</h3>
                <hr className="border-1 border-gray-500 w-full" />
                <p className="text-sm text-gray-400">No past sessions yet.</p>
            </div>
        );
    }

    return (
        <div className="flex gap-2 w-full rounded-xl flex-col items-start bg-gradient-to-b from-[#16120d] to-[#242529] p-3 shadow-lg/30 shadow-black-500">
            <h3 className="font-semibold">Recent Activity</h3>
            <hr className="border-1 border-gray-500 w-full" />

            <ul role="list" className="divide-y divide-white/5 w-full overflow-auto">
                {data.map((session) => {
                    const plannedSeconds = getWorkoutPlannedSeconds(session);
                    const calories = session.caloriesBurned ?? calculateSessionCalories(session, plannedSeconds);
                    const startedAt = session.startedAt ? new Date(session.startedAt).toLocaleDateString() : "Unknown date";

                    return (
                        <li key={session.id} className="py-4">
                            <div className="flex flex-col gap-3 rounded-2xl border border-white/10 bg-[#111215]/80 p-4 shadow-inner">
                                <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-2">
                                    <div>
                                        <p className="text-white text-sm font-semibold">{session.workout?.name || "Workout"}</p>
                                        <p className="text-xs text-gray-400">{startedAt}</p>
                                    </div>
                                    <div className="text-right">
                                        <p className="text-sm text-white">{calories} kcal</p>
                                        <p className="text-xs text-gray-400">{session.completed ? "Completed" : "In progress"}</p>
                                    </div>
                                </div>

                                <div className="grid gap-2 sm:grid-cols-2">
                                    {session.workout?.exercises?.map((exercise) => (
                                        <div key={exercise.id} className="rounded-xl bg-[#181b1f] p-3">
                                            <p className="text-sm font-semibold text-white">{exercise.name}</p>
                                            <p className="text-xs text-gray-400 mt-1">Duration: {exercise.duration || 0}s</p>
                                            <p className="text-xs text-gray-400">Sets: {exercise.sets ?? 0}</p>
                                            <p className="text-xs text-gray-400">Reps: {exercise.reps ?? 0}</p>
                                        </div>
                                    ))}
                                </div>
                            </div>
                        </li>
                    );
                })}
            </ul>
        </div>
    );
};

export default RecentActivity;
