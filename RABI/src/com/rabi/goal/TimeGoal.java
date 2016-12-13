package com.rabi.goal;

import java.util.Timer;
import java.util.TimerTask;

public class TimeGoal extends Goal {

	/**
	 * The Timer for this instance of TimeGoal.
	 */
	private Timer goalTimer;
	/**
	 * The Task for this TimeGoal's Timer to execute.
	 */
	private TimerTask goalTimerTask;
	/**
	 * The boolean denoting the current status of this TimeGoal's Timer.
	 */
	private boolean timerStarted;
	/**
	 * The number of seconds that have passed in this TimeGoal's Timer
	 * execution.
	 */
	private int secondsPassed = 0;
	/**
	 * The number of minutes that have passed in this TimeGoal's Timer
	 * execution.
	 */
	private int minutesPassed = (int) (Math.floor(secondsPassed / 60));
	/**
	 * The number of hours that have passed in this TimeGoal's Timer execution.
	 */
	private int hoursPassed = (int) (Math.floor(minutesPassed / 60));
	/**
	 * The number of days that have passed in this TimeGoal's Timer execution.
	 */
	private int daysPassed = (int) (Math.floor(hoursPassed / 24));

	/**
	 * The TimeGoal constructor.
	 * 
	 * @param goalType
	 *            Used to set this TimeGoal instance's GoalType.
	 * @param currentCompletion
	 *            Used to set this TimeGoal instance's currentCompletion.
	 * @param completionThreshold
	 *            Used to set this TimeGoal instance's completionThreshold.
	 */
	public TimeGoal(GoalType goalType, int currentCompletion,
			int completionThreshold) {
		super(goalType, currentCompletion, completionThreshold);
		this.timerStarted = false;
		this.goalTimer = new Timer();
		this.goalTimerTask = new TimerTask() {
			public void run() {
				secondsPassed++;
				setCurrentCompletion( secondsPassed );
				updateTimePassed();
				if (getCompletionPercentage() >= 100.0) {
					timerStarted = false;
					this.cancel();
				}
			}
		};
	}

	/**
	 * Returns the number of seconds that have passed in this TimeGoal's Timer
	 * execution.
	 * 
	 * @return secondsPassed
	 */
	public int getSecondsPassed() {
		return this.secondsPassed;
	}

	/**
	 * Returns the number of minutes that have passed in this TimeGoal's Timer
	 * execution.
	 * 
	 * @return minutesPassed
	 */
	public int getMinutesPassed() {
		return this.minutesPassed;
	}

	/**
	 * Returns the number of hours that have passed in this TimeGoal's Timer
	 * execution.
	 * 
	 * @return hoursPassed
	 */
	public int getHoursPassed() {
		return this.hoursPassed;
	}

	/**
	 * Returns the number of days that have passed in this TimeGoal's Timer
	 * execution.
	 * 
	 * @return daysPassed
	 */
	public int getDaysPassed() {
		return this.daysPassed;
	}
	
	public void updateTimePassed(){
		this.minutesPassed = (int) (Math.floor(secondsPassed / 60));
		this.hoursPassed = (int) (Math.floor(minutesPassed / 60));
		this.daysPassed = (int) (Math.floor(hoursPassed / 24));
		System.out.println( "Time Passed: " + String.format( "%03d", daysPassed%365 ) + ":" + String.format( "%02d", hoursPassed%24 ) + ":" + String.format( "%02d", minutesPassed%60 ) + ":" + String.format( "%02d", secondsPassed%60 ) );
	}

	/**
	 * Starts the timer for this instance of TimeGoal.
	 */
	public void startTimer() {
		this.timerStarted = true;
		goalTimer.scheduleAtFixedRate(goalTimerTask, 1000, 1000);
	}
	
	/**
	 * Returns the current status of this TimeGoal's Timer in relation to the
	 * Timer being started or not.
	 * 
	 * @return true or false
	 */
	public boolean getTimerStarted() {
		return this.timerStarted;
	}

}
