package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;

@Controller
@RequestMapping("/tasks")
public class TaskController {

	private TaskService taskService;
	private String currentUser;

	public TaskController(TaskService theTaskService) {
		taskService = theTaskService;
	}

	@GetMapping("/list")
	public String listTask(Model theModel, @RequestParam String name) {
		currentUser = name;
		System.out.println(name);

		List<Task> theTasks = taskService.findAll();
		List<Task> taskEnd = new ArrayList<>();

		for (Task task : theTasks) {
			if (task.getTask_Username().equals(name)) {
				taskEnd.add(task);
			}
		}
		theModel.addAttribute("name", name);
		theModel.addAttribute("taskList", taskEnd);
		return "task";
	}

	@GetMapping("/adminlist")
	public String adminTask(Model theModel, @RequestParam String name) {
		currentUser = name;
		List<Task> theTasks = taskService.findAll();

		for (Task task : theTasks) {
			theModel.addAttribute("taskList", theTasks);
		}

		return "adminTask";
	}

	@GetMapping("/deleteAdmin")
	public String deleteAdmin(Model theModel, @RequestParam Integer taskId) {
		taskService.deleteById(taskId);
		System.out.println(taskId + " deleted.");

		return "redirect:/tasks/adminlist?name=admin";
	}

	@GetMapping("/updateAdmin")
	public String updateAdmin(Model theModel, @RequestParam Integer taskId) {
		Task task = taskService.findByTaskId(taskId);
		System.out.println(task);
		theModel.addAttribute("task", task);
		theModel.addAttribute("name", currentUser);
		return "updateTaskForm";
	}

	@PostMapping("/saveAdmin")
	public String saveAdmin(@ModelAttribute Task task, @RequestParam String name) {

		System.out.println(task.toString());
		if (taskService.findTaskName(task.getTaskName()) == null) {
			task.setTask_Username(name);
			System.out.println(task.toString());
			taskService.save(task);
			return "redirect:/tasks/adminlist?name=" + currentUser;
		} else {
			return "redirect:/tasks/save?name=" + currentUser;
		}
	}

	@GetMapping("/save")
	public String saveTask(Model theModel, @RequestParam String name) {

		Task theTask = new Task();
		theModel.addAttribute("name", name);
		theModel.addAttribute("task", theTask);

		return "taskForm";
	}

	@PostMapping("/save")
	public String saveTask(@ModelAttribute Task task, @RequestParam String name) {
		currentUser = name;
		System.out.println(task.toString());
		if (taskService.findTaskName(task.getTaskName()) == null) {
			task.setTask_Username(name);
			System.out.println(task.toString());
			taskService.save(task);
			return "redirect:/tasks/list?name=" + name;
		} else {
			task.setTask_Username(name);
			taskService.save(task);
			System.out.println("else");
			return "redirect:/tasks/list?name=" + name;
		}
	}

	@GetMapping("/delete")
	public String delete(Model theModel, @RequestParam Integer taskId) {
		taskService.deleteById(taskId);
		System.out.println(taskId + " deleted.");
		System.out.println("else");
		return "redirect:/tasks/list?name=" + currentUser;
	}

	@GetMapping("/update")
	public String update(Model theModel, @RequestParam Integer taskId) {
		Task task = taskService.findByTaskId(taskId);
		System.out.println(task);
		theModel.addAttribute("task", task);
		theModel.addAttribute("name", currentUser);
		return "updateTaskForm";
	}

	@PostMapping("/save2")
	public String saveTask2(@ModelAttribute Task task, @RequestParam String name) {

		System.out.println("current user : " + currentUser);
		if (currentUser.equals("admin")) {

			taskService.save(task);
			return "redirect:/tasks/adminlist?name=admin";
		}
		if (taskService.findTaskName(task.getTaskName()) == null) {
			task.setTask_Username(name);
			System.out.println(task.toString());
			taskService.save(task);
			return "redirect:/tasks/list?name=" + name;
		} else {
			System.out.println("else");
			return "redirect:/tasks/save?name=" + name;
		}
	}
}
