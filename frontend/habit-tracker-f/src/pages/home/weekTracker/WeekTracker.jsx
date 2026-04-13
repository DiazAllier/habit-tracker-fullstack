import { useEffect } from "react";
import WeekCalendarProgress from "./WeekCalendarProgress";
import WorkoutSummary from "./WorkoutSummary";

export default function WeekTracker({data, totalData, formatDuration}) {


    return (
        <div className="flex flex-col sm:flex-row gap-4">
            <WeekCalendarProgress/>
            <WorkoutSummary  data={data}  formatDuration={formatDuration}/>
        </div>
    );
}