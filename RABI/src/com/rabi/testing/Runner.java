package com.rabi.testing;

import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;

import com.rabi.goal.CountGoal;
import com.rabi.goal.ExperienceGoal;
import com.rabi.goal.GoalType;
import com.rabi.goal.TimeGoal;
import com.rabi.task.Task;
import com.rabi.task.TaskExecutor;
import com.rabi.task.TaskStack;

public class Runner extends ClientAccessor{

	private static ClientContext ctx;
	
	public Runner(ClientContext ctx) {
		super(ctx);
		this.ctx = ctx;
	}

	static Task task1 = new Task("Mining Task 1", false, new TimeGoal(GoalType.TIME, 0, 120));
	static Task task2 = new Task("Mining Task 2", false, new CountGoal(GoalType.COUNT, 0, 5));
	static Task task3 = new Task("Mining Task 3", false, new ExperienceGoal(GoalType.EXPERIENCE, 0, 178));
	static TaskStack taskStack = TaskStack.getInstance();
	static TaskExecutor taskExecutor = TaskExecutor.getInstance(Runner.ctx);
	
	public static void main(String [] args){
		taskStack.pushTask(task3);
		taskStack.pushTask(task2);
		taskStack.pushTask(task1);
		taskExecutor.executeTasks();
		System.out.println("Execution Complete.");
	}
	
}
