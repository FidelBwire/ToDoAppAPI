package com.sunflash.todo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sunflash.todo.DTO.request.TaskReportRequestDTO;
import com.sunflash.todo.DTO.response.ReportResponseDTO;
import com.sunflash.todo.service.TaskReportService;

@RestController
@RequestMapping("/tasks")
public class ReportsController {

	@Autowired
	private TaskReportService reportService;

	@RequestMapping(method = { RequestMethod.PUT, RequestMethod.POST }, value = "/{taskId}/report")
	public ResponseEntity<ReportResponseDTO> createTaskReport(HttpServletRequest httpServletRequest,
			@Valid @RequestBody TaskReportRequestDTO taskReportRequestDTO,
			@PathVariable(name = "taskId", required = true) Long taskId) {
		ReportResponseDTO reportDTO = reportService.saveTaskReport(httpServletRequest, taskId, taskReportRequestDTO);
		return new ResponseEntity<ReportResponseDTO>(reportDTO, HttpStatus.OK);
	}
}
