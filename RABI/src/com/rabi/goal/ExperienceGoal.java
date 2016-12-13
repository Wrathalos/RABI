package com.rabi.goal;

public class ExperienceGoal extends Goal{

	private int experienceGained;
	
	public ExperienceGoal(GoalType goalType, int currentCompletion,
			int completionThreshold) {
		super(goalType, currentCompletion, completionThreshold);
	}
	
}
