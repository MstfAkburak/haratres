package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Task;
import com.example.demo.jpa.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

	private TaskRepository taskRepository;

	@Autowired
	public TaskServiceImpl(TaskRepository theTaskRepository) {
		taskRepository = theTaskRepository;
	}

	@Override
	public List<Task> findAll() {
		return taskRepository.findAll();
	}

	@Override
	public Task findByTaskId(int taskId) {
		Optional<Task> result = taskRepository.findById(taskId);

		Task theTask = null;

		if (result.isPresent()) {
			theTask = result.get();
		} else {
			throw new RuntimeException("Did not find user task - " + taskId);
		}
		return theTask;
	}

	@Override
	public void save(Task task) {
		taskRepository.save(task);
	}

	@Override
	public void deleteById(int taskId) {
		taskRepository.deleteById(taskId);
	}

	@Override
	public List<Task> findTaskName(String taskName) {
		return taskRepository.findBytaskName(taskName);
	}

	@Override
	public void update(Task task) {
		taskRepository.save(task);

	}


}
