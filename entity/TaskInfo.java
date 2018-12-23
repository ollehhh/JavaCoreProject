package entity;

import java.time.LocalDateTime;

public class TaskInfo {
	int id, taskId;
	String taskMessage;
	LocalDateTime date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskMessage() {
		return taskMessage;
	}

	public void setTaskMessage(String taskMessage) {
		this.taskMessage = taskMessage;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}
