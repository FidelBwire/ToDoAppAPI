package com.sunflash.todo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.sunflash.todo.DTO.request.ProjectRequestDTO;
import com.sunflash.todo.DTO.response.ProjectResponseDTO;

public interface ProjectService {

	ProjectResponseDTO createNewProject(HttpServletRequest httpServletRequest,
			@Valid ProjectRequestDTO projectCreationRequest);

	List<ProjectResponseDTO> getProjects(HttpServletRequest httpServletRequest);

	ProjectResponseDTO updateProject(HttpServletRequest httpServletRequest, Long projectId,
			@Valid ProjectRequestDTO projectUpdateRequest);

	ProjectResponseDTO getProjectById(HttpServletRequest httpServletRequest, Long projectId);

	void deleteProject(HttpServletRequest httpServletRequest, Long projectId);

}
