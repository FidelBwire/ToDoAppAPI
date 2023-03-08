package com.sunflash.todo.DTO.response;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sunflash.todo.enums.TaskStatus;

@Entity
@Table(name = "ReportsView")
public class ReportResponseDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String content;
	private Timestamp reportSubmissionDate;
	@Enumerated(EnumType.STRING)
	private TaskStatus taskStatus;
	private Timestamp reportUpdateDate;

	public ReportResponseDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
