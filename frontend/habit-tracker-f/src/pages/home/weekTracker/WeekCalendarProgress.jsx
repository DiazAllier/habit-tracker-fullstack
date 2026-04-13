export default function WeekCalendarProgress() {
    const days = [
        { label: "Mon", status: "done" },
        { label: "Tue", status: "done" },
        { label: "Wed", status: "done" },
        { label: "", status: "current" },
        { label: "Fri", status: "pending" },
        { label: "Sat", status: "empty" },
        { label: "Sun", status: "empty" },
    ];

    const getStyles = (status) => {
        switch (status) {
            case "done":
                return "bg-gray-300 text-gray-800";
            case "current":
                return "bg-gray-300 text-gray-800";
            case "pending":
                return "bg-gray-300 text-gray-500";
            case "empty":
                return "bg-gray-100 text-gray-400";
            default:
                return "bg-gray-200";
        }
    };

    return (
        <div className="flex flex-col gap-2 items-start">
            <h3 className="font-semibold">This Week</h3>

            <div className="flex gap-2 flex-wrap">
                {days.map((day, index) => (
                    <div
                        key={index}
                        className={`flex items-center justify-center px-4 py-2 rounded-xl text-sm font-medium shrink-0 ${getStyles(day.status)}`}
                    >
                        <span>
                            {day.status === "current" ? "🔥" : day.label}
                        </span>

                        <span className="text-xs mt-1">
                            {day.status === "done" && "✔"}
                            {day.status === "pending" && (
                                <svg
                                    xmlns="http://www.w3.org/2000/svg"
                                    viewBox="0 0 24 24"
                                    fill="currentColor"
                                    className="w-4 h-4 ml-1"
                                >
                                    <path
                                        fillRule="evenodd"
                                        d="M12 2.25c-5.385 0-9.75 4.365-9.75 9.75s4.365 9.75 9.75 9.75 9.75-4.365 9.75-9.75S17.385 2.25 12 2.25ZM12.75 6a.75.75 0 0 0-1.5 0v6c0 .414.336.75.75.75h4.5a.75.75 0 0 0 0-1.5h-3.75V6Z"
                                        clipRule="evenodd"
                                    />
                                </svg>
                            )}
                            {day.status === "empty" && "—"}
                        </span>
                    </div>
                ))}
            </div>

        </div>
    );
}