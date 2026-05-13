export const getWorkoutPlannedSeconds = (session) => {
  return session?.workout?.exercises?.reduce((total, exercise) => {
    return total + (exercise?.duration || 0);
  }, 0) || 0;
};

export const calculateSessionCalories = (session, elapsedSeconds) => {
  if (!session || elapsedSeconds <= 0) return 0;

  const plannedSeconds = getWorkoutPlannedSeconds(session);
  const workoutCalories = session.workout?.calories || session.caloriesBurned || 0;

  if (plannedSeconds > 0 && workoutCalories > 0) {
    const perSecond = workoutCalories / plannedSeconds;
    return Math.round(elapsedSeconds * perSecond);
  }

  const defaultCaloriesPerSecond = 0.12; // ~7 kcal / min
  return Math.round(elapsedSeconds * defaultCaloriesPerSecond);
};
