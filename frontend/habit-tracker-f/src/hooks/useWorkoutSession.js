import { useEffect, useState } from "react";
import { getUserSession } from "../api/sessionWorkoutApi";


export const useWorkoutSession = (userId) => {
    const [session, setSession] = useState(null);

    useEffect(() => {
        getUserSession(userId)
            .then(res => {setSession(res.data); console.log(session)})
            .catch(err => {console.error(err);  console.log(session);});
    }, [userId]);

    return session;
};