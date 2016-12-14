package com.rabi.task;

import java.util.Timer;
import java.util.TimerTask;

import com.rabi.goal.GoalType;
import com.rabi.goal.TimeGoal;

import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;

public class TaskExecutor extends ClientAccessor {

	private ClientContext ctx;
	private static TaskExecutor instance;
	private TaskStack taskStack = TaskStack.getInstance();
	private Timer temporaryTimer;
	private int temporarySecondsPassed = 0;
	private int temporaryOreMined = 0;
	private int temporaryExperienceGained = 0;

	private TaskExecutor(ClientContext ctx) {
		super(ctx);
		this.ctx = ctx;
		temporaryTimer = new Timer();
	}

	/**
	 * Returns the Singleton instance of the TaskExecutor
	 * 
	 * @return TaskExecutor instance
	 */
	public static TaskExecutor getInstance(ClientContext ctx) {
		if (instance == null) {
			instance = new TaskExecutor(ctx);
		}
		return instance;
	}

	/**
	 * Begins execution of the Tasks currently on the TaskStack until no more
	 * Tasks remain.
	 */
	public void executeTasks() {
		while (taskStack.getStackSize() > 0) {
			while (!taskStack.getCurrentTask().getTaskComplete()
					&& taskStack.getCurrentTask().getGoal().getCompletionPercentage() != 100.0) {
				taskStack.getCurrentTask().setTaskComplete();
				executeTaskInstruction(taskStack.getCurrentTask());
			}
			taskStack.popTask();
		}
	}

	/**
	 * Executes the instructions stored within a Task.
	 * 
	 * @param task
	 */
	private void executeTaskInstruction(final Task task) {
		while (this.playerIsInactive() && task.getGoal().getCompletionPercentage() < 100.0) {
			System.out.println("Executing " + task.getName() + "...");
			switch (task.getGoal().getGoalType()) {
			case TIME:
				try {
					if ((!((TimeGoal) task.getGoal()).getTimerStarted())) {
						((TimeGoal) task.getGoal()).startTimer();
					}
				} catch (Exception e) {
					System.out.println("Timer is already started...");
					System.out.println(e);
				}
				// Simulating Task Instruction
				temporaryTimer.scheduleAtFixedRate(new TimerTask() {
					public void run() {
						temporarySecondsPassed++;
						System.out.println(temporarySecondsPassed);
						if (temporarySecondsPassed == 10) {
							taskStack.getCurrentTask().setTaskComplete();
							temporarySecondsPassed = 0;
							this.cancel();
						}
					}
				}, 2000, 1000);
				break;
			case COUNT:
				// Simulating Task Instruction
				temporaryTimer.scheduleAtFixedRate(new TimerTask() {
					public void run() {
						temporarySecondsPassed++;
						if (temporarySecondsPassed == 10) {
							temporaryOreMined += 1;
							task.getGoal().setCurrentCompletion(temporaryOreMined);
							try {
								taskStack.getCurrentTask().setTaskComplete();
							} catch (Exception e) {
								System.out.println(e);
							}
							temporarySecondsPassed = 0;
							this.cancel();
						}
					}
				}, 1000, 1000);
				break;
			case EXPERIENCE:
				// Simulating Task Instruction
				temporaryTimer.scheduleAtFixedRate(new TimerTask() {
					public void run() {
						temporarySecondsPassed++;
						if (temporarySecondsPassed == 10) {
							temporaryOreMined += 1;
							temporaryExperienceGained += 15;
							task.getGoal().setCurrentCompletion(temporaryExperienceGained);
							try {
								taskStack.getCurrentTask().setTaskComplete();
							} catch (Exception e) {
								System.out.println(e);
							}
							temporarySecondsPassed = 0;
							this.cancel();
						}
					}
				}, 1000, 1000);
				break;
			}

		}

	}
	
	/**
	 * Checks to see if the player is inactive.
	 */
	private boolean playerIsInactive(){
		return ctx.players.local().animation() == -1;
	}

}
