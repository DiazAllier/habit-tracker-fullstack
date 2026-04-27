import WeekCalendarProgress from "./WeekCalendarProgress";
import WorkoutSummary from "./WorkoutSummary";

export default function WeekTracker({ data, allSessions, formatDuration, calories }) {
    return (
        <div className="flex flex-col sm:flex-row gap-4">
            <WeekCalendarProgress allSessions={allSessions} />
            <WorkoutSummary data={data} allSessions={allSessions} formatDuration={formatDuration} calories={calories} />
        </div>
    );
}