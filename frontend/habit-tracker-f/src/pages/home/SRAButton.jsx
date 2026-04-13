

const SRAButton = () => {

    return (

        <div className="flex gap-2 justify-around flex-col sm:flex-row">
            <button className="flex flex-col sm:flex-row items-center gap-2 whitespace-nowrap rounded-full bg-gradient-to-b from-[#242528] to-[#13120d px-6 py-3 text-sm font-bold text-white shadow-lg transition-transform hover:brightness-110 active:scale-95">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor" className="size-10">
                    <path strokeLinecap="round" strokeLinejoin="round" d="M15.91 11.672a.375.375 0 0 1 0 .656l-5.603 3.113a.375.375 0 0 1-.557-.328V8.887c0-.286.307-.466.557-.327l5.603 3.112Z" />
                    <path strokeLinecap="round" strokeLinejoin="round" d="M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z" />
                </svg>
                <span className="gap-2">Start Workout</span>
            </button>



            <button className="flex flex-col sm:flex-row items-center gap-2 whitespace-nowrap rounded-full bg-gradient-to-b from-[#242528] to-[#13120d px-6 py-3 text-sm font-bold text-white shadow-lg transition-transform hover:brightness-110 active:scale-95 ">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor" className="size-10">
                    <path strokeLinecap="round" strokeLinejoin="round" d="M17.982 18.725A7.488 7.488 0 0 0 12 15.75a7.488 7.488 0 0 0-5.982 2.975m11.963 0a9 9 0 1 0-11.963 0m11.963 0A8.966 8.966 0 0 1 12 21a8.966 8.966 0 0 1-5.982-2.275M15 9.75a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z" />
                </svg>
                <span className="gap-2">Long Run</span>
            </button>
            <button className="flex flex-col sm:flex-row items-center gap-2 whitespace-nowrap rounded-full bg-gradient-to-b from-[#242528] to-[#13120d px-6 py-3 text-sm font-bold text-white shadow-lg transition-transform hover:brightness-110 active:scale-95">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="size-10">
                    <path strokeLinecap="round" strokeLinejoin="round" d="M12 9v6m3-3H9m12 0a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z" />
                </svg>
                <span className="gap-2">Add Exercise</span>
            </button>
        </div>
    )
}

export default SRAButton;