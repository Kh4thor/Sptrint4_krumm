package ru.Egor.Malyshev.Task;

import java.util.Map;

public class Task {

	private int mainTaskId;
	private double subTaskId;
	private String name;
	private TaskProgress taskProgress;
	private Map <Double, SubTask> subTaskMap;
	
	
	public Task () {
	}
	
	public Task(int mainTaskId, String name, TaskProgress taskProgress, Map <Double, SubTask> subTaskMap) {
		this.mainTaskId = mainTaskId;
		this.name = name;
		this.taskProgress = taskProgress;
		this.subTaskMap = subTaskMap;
	}
	public Task(double subTaskId, String name, TaskProgress taskProgress) {
		this.subTaskId = subTaskId;
		this.name = name;
		this.taskProgress = taskProgress;
	}
	public Map<Double, SubTask> getSubTaskMap() {
		return subTaskMap;
	}
	public void setSubTaskMap(Map<Double, SubTask> subTaskMap) {
		this.subTaskMap = subTaskMap;
	}
	public int getMainTaskId() {
		return mainTaskId;
	}
	public void setMainTaskId(int mainTaskId) {
		this.mainTaskId = mainTaskId;
	}
	public double getSubTaskId() {
		return subTaskId;
	}
	public void setSubTaskId(double subTaskId) {
		this.subTaskId = subTaskId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TaskProgress getTaskProgress() {
		return taskProgress;
	}
	public void setTaskProgress(TaskProgress taskProgress) {
		this.taskProgress = taskProgress;
	}

	@Override
	public String toString() {
		return "Task [mainTaskId=" + mainTaskId + ", subTaskId=" + subTaskId + ", name=" + name + ", taskProgress="
				+ taskProgress + ", subTaskMap=" + subTaskMap + "]";
	}

	
	
	
	
	
	
}
