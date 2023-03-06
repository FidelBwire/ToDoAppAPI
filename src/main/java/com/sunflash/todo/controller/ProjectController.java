package com.sunflash.todo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunflash.todo.DTO.request.ProjectRequestDTO;
import com.sunflash.todo.DTO.response.ProjectResponseDTO;
import com.sunflash.todo.service.ProjectService;

@RestController
@RequestMapping(value = "projects")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<ProjectResponseDTO>> getProjects(HttpServletRequest httpServletRequest) {
		List<ProjectResponseDTO> projects = projectService.getProjects(httpServletRequest);
		return new ResponseEntity<List<ProjectResponseDTO>>(projects, HttpStatus.OK);
	}

	@GetMapping(value = "/{projectId}", produces = "application/json")
	public ResponseEntity<ProjectResponseDTO> getProjectById(HttpServletRequest httpServletRequest,
			@PathVariable(name = "projectId", required = true) Long projectId) {
		ProjectResponseDTO projectResponseDTO = projectService.getProjectById(httpServletRequest, projectId);
		return new ResponseEntity<ProjectResponseDTO>(projectResponseDTO, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<ProjectResponseDTO> createNewProject(HttpServletRequest httpServletRequest,
			@Valid @RequestBody ProjectRequestDTO projectCreationRequest) {
		ProjectResponseDTO project = projectService.createNewProject(httpServletRequest, projectCreationRequest);
		return new ResponseEntity<ProjectResponseDTO>(project, HttpStatus.OK);
	}

	@PutMapping(value = "/{projectId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ProjectResponseDTO> updateProject(HttpServletRequest httpServletRequest,
			@Valid @RequestBody ProjectRequestDTO projectUpdateRequest,
			@PathVariable(name = "projectId", required = true) Long projectId) {
		ProjectResponseDTO project = projectService.updateProject(httpServletRequest, projectId, projectUpdateRequest);
		return new ResponseEntity<ProjectResponseDTO>(project, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{projectId}")
	public ResponseEntity<String> deleteProject(HttpServletRequest httpServletRequest,
			@PathVariable(name = "projectId", required = true) Long projectId) {
		projectService.deleteProject(httpServletRequest, projectId);
		return new ResponseEntity("Deleted Successfully", HttpStatus.OK);
	}

}
