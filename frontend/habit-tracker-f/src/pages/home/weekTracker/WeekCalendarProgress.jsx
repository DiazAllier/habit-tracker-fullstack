export default function WeekCalendarProgress({ allSessions }) {
    const dayLabels = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"];

    const now = new Date();
    const dayOfWeek = now.getDay(); // 0 = Sun
    const diffToMonday = (dayOfWeek + 6) % 7;
    const monday = new Date(now);
    monday.setDate(now.getDate() - diffToMonday);
    monday.setHours(0, 0, 0, 0);

    const completedDays = new Set();
    const runDays = new Set();

    (allSessions ?? []).forEach(s => {
        if (!s.completed) return;
        const d = new Date(s.startedAt);
        const diff = Math.floor((d - monday) / (1000 * 60 * 60 * 24));
        if (diff >= 0 && diff <= 6) {
            completedDays.add(diff);
            if (s.sessionType === "CARDIO") runDays.add(diff);
        }
    });

    const todayIndex = diffToMonday; 

    const getStatus = (index) => {
        if (completedDays.has(index)) return "done";
        if (index === todayIndex) return "current";
        if (index < todayIndex) return "pending"; 
        return "empty";
    };

    const getStyles = (status) => {
        switch (status) {
            case "done":    return "bg-gray-300 text-gray-800";
            case "current": return "bg-gray-300 text-gray-800";
            case "pending": return "bg-gray-300 text-gray-500";
            case "empty":   return "bg-gray-100 text-gray-400";
            default:        return "bg-gray-200";
        }
    };

    const getIcon = (status, index) => {
        if (status === "done") {
            return runDays.has(index) ? "🏃" : "✔";
        }
        if (status === "current") return "🔥";
        if (status === "pending") return (
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" className="w-4 h-4 ml-1">
                <path fillRule="evenodd" d="M12 2.25c-5.385 0-9.75 4.365-9.75 9.75s4.365 9.75 9.75 9.75 9.75-4.365 9.75-9.75S17.385 2.25 12 2.25ZM12.75 6a.75.75 0 0 0-1.5 0v6c0 .414.336.75.75.75h4.5a.75.75 0 0 0 0-1.5h-3.75V6Z" clipRule="evenodd" />
            </svg>
        );
        return "—";
    };

    return (
        <div className="flex flex-col gap-2 items-start">
            <h3 className="font-semibold">This Week</h3>
            <div className="flex gap-2 flex-wrap">
                {dayLabels.map((label, index) => {
                    const status = getStatus(index);
                    return (
                        <div
                            key={index}
                            className={`flex items-center justify-center px-4 py-2 rounded-xl text-sm font-medium shrink-0 ${getStyles(status)}`}
                        >
                            <span>{status === "current" ? "" : label}</span>
                            <span className="text-xs mt-1 flex items-center">
                                {getIcon(status, index)}
                            </span>
                        </div>
                    );
                })}
            </div>
        </div>
    );
}