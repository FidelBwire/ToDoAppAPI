package com.sunflash.todo.DTO.response;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sunflash.todo.enums.TaskStatus;

@Entity
@Table(name = "TasksView")
public class TaskResponseDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	private Timestamp scheduledStartDate = new Timestamp(System.currentTimeMillis());
	private Timestamp scheduledCompletionDate;
	private Long projectId;
	private String projectTitle;
	private String createdBy;
	private Timestamp createdOn;
	@Column(name = "created_by_id")
	private Long creatorId;
	private String updatedBy;
	private Timestamp updatedOn;
	@Column(name = "updated_by_id")
	private Long updatorId;
	@Transient
	private Set<String> asignedUsers;
	private Long reportId;
	@Column(name = "content")
	private String report;
	private Timestamp reportSubmissionDate;
	@Enumerated(EnumType.STRING)
	private TaskStatus taskStatus;
	private Timestamp reportUpdateDate;

	public TaskResponseDTO() {
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

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Long getUpdatorId() {
		return updatorId;
	}

	public void setUpdatorId(Long updatorId) {
		this.updatorId = updatorId;
	}

	public Set<String> getAsignedUsers() {
		return asignedUsers;
	}

	public void setAsignedUsers(Set<String> asignedUsers) {
		this.asignedUsers = asignedUsers;
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public Timestamp getReportSubmissionDate() {
		return reportSubmissionDate;
	}

	public void setReportSubmissionDate(Timestamp reportSubmissionDate) {
		this.reportSubmissionDate = reportSubmissionDate;
	}

	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Timestamp getReportUpdateDate() {
		return reportUpdateDate;
	}

	public void setReportUpdateDate(Timestamp reportUpdateDate) {
		this.reportUpdateDate = reportUpdateDate;
	}

}
