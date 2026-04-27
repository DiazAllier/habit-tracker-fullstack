import { useState, useRef, useEffect } from "react";

const DropDown = ({items}) => {
    const [open, setOpen] = useState(false);
    const [selected, setSelected] = useState("Select workout");
    const ref = useRef();

    useEffect(() => {
        const handleClickOutside = (e) => {
            if (ref.current && !ref.current.contains(e.target)) {
                setOpen(false);
            }
        };
        document.addEventListener("click", handleClickOutside);
        return () => document.removeEventListener("click", handleClickOutside);
    }, []);

    const normalizedItems = Array.isArray(items) ? items : [];

    return (
        <div ref={ref} className="relative w-56">
            <button
                onClick={() => setOpen(!open)}
                className="flex items-center justify-between w-full px-5 py-3 text-left text-sm font-medium text-white bg-gradient-to-b from-[#242528] to-[#13120d] border border-gray-800 rounded-xl shadow-[0_18px_50px_rgba(0,0,0,0.25)] transition duration-300 ease-in-out transform hover:-translate-y-0.5 hover:border-gray-600 hover:shadow-2xl focus:outline-none focus:ring-2 focus:ring-orange-400"
            >
                <span>{selected}</span>
                <span className={`transition-transform duration-300 ${open ? "rotate-180" : "rotate-0"}`}>
                    ▾
                </span>
            </button>

            {open && (
                <div className="absolute z-50 mt-2 w-full bg-[#191b1f] border border-gray-800 rounded-2xl shadow-2xl max-h-56 overflow-y-auto">
                    <ul className="text-sm text-white">
                        {normalizedItems.length > 0 ? (
                            normalizedItems.map((item, index) => (
                                <li
                                    key={index}
                                    onClick={() => {
                                        setSelected(item.name || item);
                                        setOpen(false);
                                    }}
                                    className="px-4 py-3 rounded-lg cursor-pointer hover:bg-white/10 transition-colors duration-200"
                                >
                                    {item.name || item}
                                </li>
                            ))
                        ) : (
                            <li className="px-4 py-3 text-gray-400">No workouts available</li>
                        )}
                    </ul>
                </div>
            )}
        </div>
    );
}

export default DropDown;