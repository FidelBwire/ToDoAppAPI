package com.sunflash.todo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunflash.todo.DTO.response.ProjectResponseDTO;

public interface ProjectViewRepository extends JpaRepository<ProjectResponseDTO, Long> {

	List<ProjectResponseDTO> findByCreatedBy(String username);

	Optional<ProjectResponseDTO> findByCreatedByAndId(String username, Long projectId);

	int existsByCreatedByAndId(String username, Long projectId);

}
