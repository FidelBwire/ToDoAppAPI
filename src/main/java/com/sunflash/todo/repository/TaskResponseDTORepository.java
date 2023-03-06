package com.sunflash.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunflash.todo.DTO.response.TaskResponseDTO;

public interface TaskResponseDTORepository extends JpaRepository<TaskResponseDTO, Long> {

}
