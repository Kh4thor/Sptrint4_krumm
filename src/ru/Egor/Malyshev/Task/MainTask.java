package ru.Egor.Malyshev.Task;

import java.util.Map;

public class MainTask extends Task {

	public MainTask (int id, String name, TaskProgress taskProgress, Map <Double, SubTask> subTaskMap) {
		super(id, name, taskProgress, subTaskMap);
	}

//	@Override
//	public String toString() {
//		return "MainTask [mainTaskId()=" + getMainTaskId()+ ", getName()=" + getName() + ", getTaskProgress()="
//				+ getTaskProgress() + ", hashCode()=" + hashCode() + "]";
//	}
	
	
	
}
