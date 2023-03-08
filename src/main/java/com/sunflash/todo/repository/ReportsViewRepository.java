package com.sunflash.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunflash.todo.DTO.response.ReportResponseDTO;

public interface ReportsViewRepository extends JpaRepository<ReportResponseDTO, Long> {

}
