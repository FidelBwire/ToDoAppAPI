package com.sunflash.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunflash.todo.DTO.response.TaskResponseDTO;

public interface TaskViewRepository extends JpaRepository<TaskResponseDTO, Long> {

}
