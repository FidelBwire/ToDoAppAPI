package com.sunflash.todo.DTO.response;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ProjectsView")
public class ProjectResponseDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	private Timestamp scheduledStartDate;
	private Timestamp scheduledCompletionDate;
	private String createdBy;
	private Timestamp createdAt;

	public ProjectResponseDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "ProjectResponseDTO [id=" + id + ", title=" + title + ", description=" + description
				+ ", scheduledStartDate=" + scheduledStartDate + ", scheduledCompletionDate=" + scheduledCompletionDate
				+ ", createdBy=" + createdBy + ", createdAt=" + createdAt + "]";
	}

}
