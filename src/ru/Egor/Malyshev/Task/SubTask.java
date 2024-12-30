package ru.Egor.Malyshev.Task;

public class SubTask extends Task {

	public SubTask () {
	};
	
	public SubTask(Double subTaskId, String name, TaskProgress progressStatus) {
		super(subTaskId, name, progressStatus);
	}

//	@Override
//	public String toString() {
//		return "SubTask [mainTaskId()=" + getMainTaskId() + ", subTaskId()=" + getSubTaskId() + ", subTaskName()=" + getName() + ", subTaskProgress()="
//				+ getTaskProgress() + "hashCode()=" + hashCode() + "]";
//	}
	
	
	
}
