package com.sunflash.todo.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "Tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<User> asignedUsers;

	@ManyToOne(optional = true)
	private Project project;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String description;

	@FutureOrPresent
	@Column
	private Timestamp scheduledStartDate;

	@FutureOrPresent
	@Column
	private Timestamp scheduledCompletionDate;

	@ManyToOne(fetch = FetchType.LAZY)
	private User createdBy;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private Timestamp createdOn;

	@ManyToOne(optional = true)
	private User updatedBy;

	@UpdateTimestamp
	@Column
	private Timestamp updatedOn;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true, mappedBy = "task")
	private TaskReport report;

	public Task() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<User> getAsignedUsers() {
		return asignedUsers;
	}

	public void setAsignedUsers(List<User> asignedUsers) {
		this.asignedUsers = asignedUsers;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

	public TaskReport getReport() {
		return report;
	}

	public void setReport(TaskReport report) {
		this.report = report;
	}

}
