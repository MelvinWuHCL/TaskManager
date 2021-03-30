package com.example.TaskManager.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.TaskManager.entities.Task;
import com.example.TaskManager.entities.User;
import com.example.TaskManager.repositories.TaskRepository;

@Service
public class TaskService {

	Logger log = LoggerFactory.getLogger(TaskService.class);

	@Autowired
	private TaskRepository taskRepository;

	public Iterable<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	public Task getTaskbyName(String taskname) {

		return taskRepository.findByName(taskname);
	}

	public Iterable<Task> getTasksByUserId(User user) {

		return (taskRepository.findAllByUser(user));
	}

//	@Autowired
	public Task save(Task task) {

		return taskRepository.save(task);
	}
}
