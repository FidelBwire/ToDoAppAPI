package com.sunflash.todo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sunflash.todo.DTO.response.TaskResponseDTO;

public interface TaskPagingRepository extends PagingAndSortingRepository<TaskResponseDTO, Long> {

}
