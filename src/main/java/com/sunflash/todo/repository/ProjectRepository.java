package com.sunflash.todo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunflash.todo.model.Project;
import com.sunflash.todo.model.User;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	List<Project> findByCreatedBy(User user);

	Optional<Project> findByCreatedByAndId(User user, Long projectId);

	boolean existsByIdAndCreatedBy(Long projectId, User user);

}
