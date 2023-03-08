package com.sunflash.todo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunflash.todo.model.Task;
import com.sunflash.todo.model.TaskReport;

public interface TaskReportRepository extends JpaRepository<TaskReport, Long> {

	Optional<TaskReport> findByTask(Task task);

}
