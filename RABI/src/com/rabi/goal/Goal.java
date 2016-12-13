package com.rabi.goal;

public class Goal {

	/**
	 * The GoalType enum for this instance of Goal.
	 */
	private GoalType goalType;
	/**
	 * The current progress towards completing this instance of Goal.
	 */
	private int currentCompletion;
	/**
	 * The progress needed to complete this instance of Goal.
	 */
	private int completionThreshold;

	/**
	 * The Goal constructor
	 * 
	 * @param goalType
	 *            Used to set this Goal instance's GoalType.
	 * @param currentCompletion
	 *            Used to set this Goal instance's currentCompletion.
	 * @param completionThreshold
	 *            Used to set this Goal instance's completionThreshold.
	 */
	public Goal(GoalType goalType, int currentCompletion,
			int completionThreshold) {
		this.goalType = goalType;
		this.currentCompletion = currentCompletion;
		this.completionThreshold = completionThreshold;
	}

	/**
	 * Returns the goalType for this instance of Goal.
	 * 
	 * @return goalType
	 */
	public GoalType getGoalType() {
		return this.goalType;
	}

	/**
	 * Returns the currentCompletion for this instance of Goal.
	 * 
	 * @return currentCompletion
	 */
	public int getCurrentCompletion() {
		return this.currentCompletion;
	}

	/**
	 * Sets the currentCompletion for this instance of Goal to the value passed
	 * in via the newCompletion parameter.
	 * 
	 * @param newCompletion
	 *            The new value to set the currentCompletion for this instance
	 *            of Goal to.
	 */
	public void setCurrentCompletion(int newCompletion) {
		this.currentCompletion = newCompletion;
		System.out.println("Completion Percentage: "
				+ String.format("%.2f", getCompletionPercentage()));
	}

	/**
	 * Returns the completionThreshold for this instance of Goal.
	 * 
	 * @return completionThreshold.
	 */
	public int getCompletionThreshold() {
		return this.completionThreshold;
	}

	/**
	 * Returns a double representing the current completion percentage for this
	 * instance of Goal.
	 * 
	 * @return ##.##
	 */
	public double getCompletionPercentage() {
		return (((double) currentCompletion) / ((double) completionThreshold) * 100);
	}

}