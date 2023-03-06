package com.sunflash.todo.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;

import com.sunflash.todo.DTO.request.TaskRequestDTO;
import com.sunflash.todo.DTO.response.TaskResponseDTO;

public interface TaskService {

	TaskResponseDTO createTask(HttpServletRequest httpServletRequest, TaskRequestDTO taskRequestDTO);

	Page<TaskResponseDTO> getTasks(HttpServletRequest httpServletRequest, int page, int size, String sortBy,
			String direction);

}
