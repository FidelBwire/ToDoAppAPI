package com.sunflash.todo.service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.sunflash.todo.DTO.request.TaskReportRequestDTO;
import com.sunflash.todo.DTO.response.ReportResponseDTO;

public interface TaskReportService {

	ReportResponseDTO saveTaskReport(HttpServletRequest httpServletRequest, Long taskId,
			@Valid TaskReportRequestDTO taskReportRequestDTO);

}
