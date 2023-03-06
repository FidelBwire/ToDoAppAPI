package com.sunflash.todo.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sunflash.todo.DTO.request.TaskRequestDTO;
import com.sunflash.todo.DTO.response.TaskResponseDTO;
import com.sunflash.todo.model.Project;
import com.sunflash.todo.model.Task;
import com.sunflash.todo.model.User;
import com.sunflash.todo.repository.ProjectRepository;
import com.sunflash.todo.repository.TaskPagingRepository;
import com.sunflash.todo.repository.TaskRepository;
import com.sunflash.todo.repository.TaskResponseDTORepository;
import com.sunflash.todo.repository.UserRepository;
import com.sunflash.todo.service.TaskService;
import com.sunflash.todo.service.UserAuthService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private TaskPagingRepository taskPagingRepository;

	@Autowired
	private TaskResponseDTORepository taskResponseDTORepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserAuthService userAuthService;

	@Override
	public Page<TaskResponseDTO> getTasks(HttpServletRequest httpServletRequest, int page, int size, String sortBy,
			String direction) {
		Sort sort = ("ascending".equalsIgnoreCase(direction)) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		PageRequest pageRequest = PageRequest.of(page, size, sort);
		Page<TaskResponseDTO> tasksPage = taskPagingRepository.findAll(pageRequest);

		return tasksPage;
	}

	@Override
	public TaskResponseDTO createTask(HttpServletRequest httpServletRequest, TaskRequestDTO taskRequestDTO) {
		Task task = convertRequestToTask(taskRequestDTO);
		User userFromRequest = userAuthService.parseUserFromRequest(httpServletRequest);
		task.setCreatedBy(userFromRequest);
		Task savedTask = taskRepository.save(task);

		TaskResponseDTO taskResponseDTO = taskResponseDTORepository.findById(savedTask.getId()).orElse(null);
		if (taskResponseDTO != null) {
			Task taskItem = taskRepository.findById(savedTask.getId()).orElse(null);
			if (taskItem != null) {
				Set<String> users = new HashSet<>();
				taskItem.getAsignedUsers().forEach(user -> {
					users.add(user.getUsername());
				});
				taskResponseDTO.setAsignedUsers(users);
			}
		}
		return taskResponseDTO;
	}

	private Task convertRequestToTask(TaskRequestDTO taskRequestDTO) {
		Task task = new Task();
		task.setTitle(taskRequestDTO.getTitle());
		task.setDescription(taskRequestDTO.getDescription());
		task.setScheduledStartDate(taskRequestDTO.getScheduledStartDate());
		task.setScheduledCompletionDate(taskRequestDTO.getScheduledCompletionDate());

		if (taskRequestDTO.getProjectId() != null) {
			Project project = projectRepository.findById(taskRequestDTO.getProjectId()).orElse(null);
			if (project != null) {
				task.setProject(project);
			}
		}

		List<User> asignedUsers = new ArrayList<>();

		if (taskRequestDTO.getAsignedUsers().size() > 0) {
			taskRequestDTO.getAsignedUsers().forEach(asignedUser -> {
				System.out.println("Point 5");
				User user = userRepository.findByUsername(asignedUser).orElse(null);
				if (user != null)
					asignedUsers.add(user);
			});
			if (asignedUsers.size() > 0)
				task.setAsignedUsers(asignedUsers);
		}
		return task;
	}
}
