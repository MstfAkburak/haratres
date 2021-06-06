package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Task;

public interface TaskService {

	public List<Task> findAll();
	
	public List<Task> findTaskName(String taskName);

	public Task findByTaskId(int taskId);

	public void save(Task task);

	public void deleteById(int taskId);

	public void update(Task task);
	
}
