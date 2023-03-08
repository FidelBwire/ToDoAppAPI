package com.sunflash.todo.DTO.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.sunflash.todo.custom_validations.annotation.TaskStatus;

public class TaskReportRequestDTO {
	@Size(min = 1, max = 250, message = "Report content must be between 1 to 250 characters")
	@NotNull(message = "Report content must not be null")
	private String content;
	@NotNull(message = "Please specify the task status")
	@TaskStatus
	private String taskStatus;

	public TaskReportRequestDTO() {
		super();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

}
