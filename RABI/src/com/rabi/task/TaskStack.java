package com.rabi.task;

import java.util.ArrayList;

public class TaskStack {
	
	private static TaskStack instance;
	private ArrayList<Task> taskStack;
	
	private TaskStack(){
		taskStack = new ArrayList<Task>();
	}
	
	/**
	 * Returns the Singleton instance of the TaskStack
	 * 
	 * @return TaskStack instance
	 */
	public static TaskStack getInstance (){
		if (instance == null){
			instance = new TaskStack();
		}
		return instance;
	}
	
	/**
	 * Appends a new Task to the end of the TaskStack
	 * 
	 * @param task Task to push to the TaskStack
	 */
	public void pushTask(Task task){
		taskStack.add(task);
	}
	
	/**
	 * Removes the last Task from the TaskStack
	 */
	public void popTask(){
		taskStack.remove(taskStack.size()-1);
	}
	
	/**
	 * Returns the last Task on the TaskStack
	 * 
	 * @return Last Task on the TaskStack
	 */
	public Task getCurrentTask(){
		return taskStack.get(taskStack.size()-1);
	}
	
	/**
	 * Returns the current size of the TaskStack
	 * 
	 * @return The current size of the TaskStack
	 */
	public int getStackSize(){
		return taskStack.size();
	}
	
}
