package com.sunflash.todo.service.impl;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunflash.todo.DTO.request.TaskReportRequestDTO;
import com.sunflash.todo.DTO.response.ReportResponseDTO;
import com.sunflash.todo.custom_exceptions.ResourceNotFoundException;
import com.sunflash.todo.enums.TaskStatus;
import com.sunflash.todo.model.Task;
import com.sunflash.todo.model.TaskReport;
import com.sunflash.todo.repository.ReportsViewRepository;
import com.sunflash.todo.repository.TaskReportRepository;
import com.sunflash.todo.repository.TaskRepository;
import com.sunflash.todo.service.TaskReportService;
import com.sunflash.todo.service.UserAuthService;

@Service
public class TaskReportServiceImpl implements TaskReportService {

	@Autowired
	private TaskReportRepository taskReportRepository;

	@Autowired
	private ReportsViewRepository reportsViewRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private UserAuthService authService;

	@Override
	public ReportResponseDTO saveTaskReport(HttpServletRequest httpServletRequest, Long taskId,
			@Valid TaskReportRequestDTO taskReportRequestDTO) {
		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task " + taskId + " not found"));

		TaskReport taskReport = taskReportRepository.findByTask(task).orElse(null);
		if (taskReport == null) {
			taskReport = new TaskReport();
			taskReport.setTask(task);
			taskReport.setPostedBy(authService.parseUserFromRequest(httpServletRequest));
			taskReport.setPostedOn(new Timestamp(System.currentTimeMillis()));
		} else {
			taskReport.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
			taskReport.setUpdatedBy(authService.parseUserFromRequest(httpServletRequest));
		}

		taskReport.setContent(taskReportRequestDTO.getContent());

		String status = taskReportRequestDTO.getTaskStatus();
		TaskStatus taskStatus = getTaskStatus(status);
		taskReport.setTaskStatus(taskStatus);

		TaskReport savedReport = taskReportRepository.save(taskReport);
		ReportResponseDTO reportResponseDTO = reportsViewRepository.findById(savedReport.getId()).orElse(null);
		return reportResponseDTO;
	}

	private TaskStatus getTaskStatus(String status) {
		TaskStatus taskStatus = null;
		switch (status) {
		case "NOT_STARTED":
			taskStatus = TaskStatus.NOT_STARTED;
			break;
		case "STARTED":
			taskStatus = TaskStatus.STARTED;
			break;
		case "IN_PROGRESS":
			taskStatus = TaskStatus.IN_PROGRESS;
			break;
		case "PAUSED":
			taskStatus = TaskStatus.PAUSED;
			break;
		case "RESUMED":
			taskStatus = TaskStatus.RESUMED;
			break;
		case "ABORTED":
			taskStatus = TaskStatus.ABORTED;
			break;
		case "FAILED":
			taskStatus = TaskStatus.FAILED;
			break;
		default:
			taskStatus = TaskStatus.COMPLETED;
			break;
		}
		return taskStatus;
	}

}
