package com.sunflash.todo.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "Projects")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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

	@ManyToOne(optional = false)
	private User createdBy;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private Timestamp createdAt;

//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "project")
//	private List<Task> tasks;

	public Project() {
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

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

}
