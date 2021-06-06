package com.example.demo.entity;

import java.sql.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "tasks")
public class Task {

	// task_id task_username task_name task_date task_statement task_status

	@Id
	@Column(name = "task_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer taskId;

	@Column(name = "task_username")
	private String task_Username;

	@Column(name = "task_name")
	private String taskName;

	@Column(name = "task_date")
	private Date task_Date;

	@Column(name = "task_statement")
	private String taskStatement;

	@Column(name = "task_status")
	private String taskStatus;

	public Task() {
	}

	public Task(String task_Username, String taskName, Date task_Date, String taskStatement, String taskStatus) {
		this.task_Username = task_Username;
		this.taskName = taskName;
		this.task_Date = task_Date;
		this.taskStatement = taskStatement;
		this.taskStatus = taskStatus;
	}

	public Task(Integer taskId, String task_Username, String taskName, Date task_Date, String taskStatement,
			String taskStatus) {
		this.task_Username = task_Username;
		this.taskId = taskId;
		this.taskName = taskName;
		this.task_Date = task_Date;
		this.taskStatement = taskStatement;
		this.taskStatus = taskStatus;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getTask_Username() {
		return task_Username;
	}

	public void setTask_Username(String task_Username) {
		this.task_Username = task_Username;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Date getTask_Date() {
		return task_Date;
	}

	public void setTask_Date(Date task_Date) {
		this.task_Date = task_Date;
	}

	public String getTaskStatement() {
		return taskStatement;
	}

	public void setTaskStatement(String taskStatement) {
		this.taskStatement = taskStatement;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", task_Username=" + task_Username + ", taskName=" + taskName + ", task_Date="
				+ task_Date + ", taskStatement=" + taskStatement + ", taskStatus=" + taskStatus + "]";
	}

	

}
