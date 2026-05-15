
export default function WorkoutSummary({ data, formatDuration, calories }) {

    return (
        <div className="flex flex-col sm:flex-row gap-4 items-center justify-center flex-wrap">
            <div className="p-5 w-full sm:w-48 lg:w-48 rounded-2xl bg-gradient-to-br from-green-600 to-green-800 text-white shadow-[0_10px_30px_rgba(34,197,94,0.4)] hover:brightness-110">
                <h2 className="text-xl opacity-80">Workouts</h2>
                <p className="text-l opacity-80"> <span className="text-3xl font-bold mt-1 px-2">{data?.reduce((total, session) => total + session.workout.exercises.length, 0)}</span>this week</p>
            </div>


            <div className="p-5 w-full sm:w-48 lg:w-48 rounded-2xl bg-gradient-to-br from-blue-500 to-blue-700 text-white shadow-[0_10px_30px_rgba(59,130,246,0.4)] hover:brightness-110">
                <p className="text-xl opacity-80">Time</p>
                <p className="text-l opacity-80">
                    <span className="text-3xl font-bold mt-1">{formatDuration()} </span>
                </p>
            </div>

            <div className="p-5 w-full sm:w-48 lg:w-48 rounded-2xl bg-gradient-to-br from-orange-400 to-orange-600 text-white shadow-[0_10px_30px_rgba(249,115,22,0.4)] hover:brightness-110">
                <h2 className="text-xl opacity-80">Calories</h2>
                <p className="text-l opacity-80"> <span className="text-3xl font-bold mt-1">{calories} </span>kcal</p>
            </div>

        </div>
    );
}