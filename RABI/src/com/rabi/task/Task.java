package com.rabi.task;

import com.rabi.goal.Goal;

public class Task {
	
	private String name;
	private boolean taskComplete;
	private Goal goal;
	
	public Task(String name, boolean taskComplete, Goal goal){
		this.name = name;
		this.taskComplete = taskComplete;
		this.goal = goal;
	}
	
	public String getName(){
		return this.name;
	}
	
	public boolean getTaskComplete(){
		return this.taskComplete;
	}
	
	public Goal getGoal(){
		return this.goal;
	}
	
	public void setTaskComplete(){
		if(this.goal.getCompletionPercentage() == 100.0){
			this.taskComplete = true;
		}else{
			this.taskComplete = false;
		}
	}

}
