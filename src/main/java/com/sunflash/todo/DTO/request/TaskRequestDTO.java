package com.sunflash.todo.DTO.request;

import java.sql.Timestamp;
import java.util.Set;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TaskRequestDTO {

	@Size(min = 1, max = 100, message = "Title must be between 1 to 100 characters")
	@NotNull(message = "Title must not be null")
	private String title;

	@Size(min = 1, max = 250, message = "Description must be between 1 to 250 characters")
	@NotNull(message = "Description must not be null")
	private String description;

	@FutureOrPresent(message = "Scheduled start date must be a present or future date")
	private Timestamp scheduledStartDate = new Timestamp(System.currentTimeMillis());

	@Future(message = "Scheduled completion date must be a future date")
	private Timestamp scheduledCompletionDate;

	private Set<String> asignedUsers;

	private Long projectId;

	public TaskRequestDTO() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getScheduledStartDate() {
		return scheduledStartDate;
	}

	public void setScheduledStartDate(Timestamp scheduledStartDate) {
		this.scheduledStartDate = scheduledStartDate;
	}

	public Timestamp getScheduledCompletionDate() {
		return scheduledCompletionDate;
	}

	public void setScheduledCompletionDate(Timestamp scheduledCompletionDate) {
		this.scheduledCompletionDate = scheduledCompletionDate;
	}

	public Set<String> getAsignedUsers() {
		return asignedUsers;
	}

	public void setAsignedUsers(Set<String> asignedUsers) {
		this.asignedUsers = asignedUsers;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

}
