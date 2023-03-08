package com.sunflash.todo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunflash.todo.DTO.request.TaskRequestDTO;
import com.sunflash.todo.DTO.response.TaskResponseDTO;
import com.sunflash.todo.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@GetMapping
	public ResponseEntity<Page<TaskResponseDTO>> getTasks(HttpServletRequest httpServletRequest,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size,
			@RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
			@RequestParam(name = "direction", defaultValue = "ascending") String direction) {
		Page<TaskResponseDTO> taskResponses = taskService.getTasks(httpServletRequest, (page <= 0) ? 0 : page - 1, size,
				sortBy, direction);
		return new ResponseEntity<Page<TaskResponseDTO>>(taskResponses, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<TaskResponseDTO> createTask(HttpServletRequest httpServletRequest,
			@Valid @RequestBody TaskRequestDTO taskRequestDTO) {
		TaskResponseDTO taskResponseDTO = taskService.createTask(httpServletRequest, taskRequestDTO);
		return new ResponseEntity<TaskResponseDTO>(taskResponseDTO, HttpStatus.OK);
	}

	@PutMapping("/{taskId}")
	public ResponseEntity<TaskResponseDTO> updateTask(HttpServletRequest httpServletRequest,
			@Valid @RequestBody TaskRequestDTO taskRequestDTO,
			@PathVariable(name = "taskId", required = true) Long taskId) {
		TaskResponseDTO taskResponseDTO = taskService.updateTask(httpServletRequest, taskId, taskRequestDTO);
		return new ResponseEntity<TaskResponseDTO>(taskResponseDTO, HttpStatus.OK);
	}
}
