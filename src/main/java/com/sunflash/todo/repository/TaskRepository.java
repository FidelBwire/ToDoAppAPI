package com.sunflash.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunflash.todo.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
