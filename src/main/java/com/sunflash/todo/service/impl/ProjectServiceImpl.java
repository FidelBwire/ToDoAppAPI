package com.sunflash.todo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunflash.todo.DTO.request.ProjectRequestDTO;
import com.sunflash.todo.DTO.response.ProjectResponseDTO;
import com.sunflash.todo.custom_exceptions.ResourceNotFoundException;
import com.sunflash.todo.enums.UserRole;
import com.sunflash.todo.model.Project;
import com.sunflash.todo.model.User;
import com.sunflash.todo.repository.ProjectRepository;
import com.sunflash.todo.repository.ProjectResponseDTORepository;
import com.sunflash.todo.service.ProjectService;
import com.sunflash.todo.service.UserAuthService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ProjectResponseDTORepository projectResponseDTORepository;

	@Autowired
	private UserAuthService userAuthService;

	@Override
	public List<ProjectResponseDTO> getProjects(HttpServletRequest httpServletRequest) {
		User user = userAuthService.parseUserFromRequest(httpServletRequest);
		if (user == null)
			return new ArrayList<>();
		List<ProjectResponseDTO> projects = projectResponseDTORepository.findByCreatedBy(user.getUsername());
		return projects;
	}

	@Override
	public ProjectResponseDTO getProjectById(HttpServletRequest httpServletRequest, Long projectId) {
		User user = userAuthService.parseUserFromRequest(httpServletRequest);
		ProjectResponseDTO projectResponseDTO = projectResponseDTORepository
				.findByCreatedByAndId(user.getUsername(), projectId).orElseThrow(() -> new ResourceNotFoundException(
						"Project with Id " + projectId + " not found among user's projects"));
		return projectResponseDTO;
	}

	@Override
	public ProjectResponseDTO createNewProject(HttpServletRequest httpServletRequest,
			@Valid ProjectRequestDTO projectCreationRequest) {
		Project project = createProjectInstanceFromRequest(projectCreationRequest);
		User user = userAuthService.parseUserFromRequest(httpServletRequest);
		project.setCreatedBy(user);
		Project savedProject = projectRepository.save(project);

		ProjectResponseDTO projectResponseDTO = projectResponseDTORepository.findById(savedProject.getId())
				.orElse(null);
		return projectResponseDTO;
	}

	private Project createProjectInstanceFromRequest(ProjectRequestDTO projectCreationRequest) {
		Project project = new Project();
		project.setTitle(projectCreationRequest.getTitle());
		project.setDescription(projectCreationRequest.getDescription());
		project.setScheduledStartDate(projectCreationRequest.getScheduledStartDate());
		project.setScheduledCompletionDate(projectCreationRequest.getScheduledCompletionDate());

		return project;
	}

	@Override
	public ProjectResponseDTO updateProject(HttpServletRequest httpServletRequest, Long projectId,
			@Valid ProjectRequestDTO projectUpdateRequest) {

		User user = userAuthService.parseUserFromRequest(httpServletRequest);

		// A project can only be updated by either the admin or the owner
		if ((user.getRoles().contains(UserRole.ROLE_ADMIN) && projectRepository.existsById(projectId))
				|| projectRepository.existsByIdAndCreatedBy(projectId, user)) {

			Project project = projectRepository.findById(projectId).orElse(null);
			Project requestProject = createProjectInstanceFromRequest(projectUpdateRequest);
			requestProject.setId(project.getId());
			requestProject.setCreatedAt(project.getCreatedAt());
			requestProject.setCreatedBy(project.getCreatedBy());

			Project updatedProject = projectRepository.save(requestProject);

			ProjectResponseDTO projectResponseDTO = projectResponseDTORepository.findById(projectId).orElse(null);
			return projectResponseDTO;
		} else {
			throw new ResourceNotFoundException("Project with Id " + projectId + " not found.");
		}

	}

	@Override
	public void deleteProject(HttpServletRequest httpServletRequest, Long projectId) {

	}

}
