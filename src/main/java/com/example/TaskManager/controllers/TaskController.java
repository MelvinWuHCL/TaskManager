package com.example.TaskManager.controllers;

import java.sql.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TaskManager.entities.*;
import com.example.TaskManager.services.*;

@Controller
public class TaskController {

	@Autowired
	private UserService userService;

	@Autowired
	private TaskService taskService;

	private Logger log = LoggerFactory.getLogger(TaskController.class);

	@GetMapping(value = "/")
	public String showHome() {
		log.info("Starting up project");
		return "Home";
	}

	@GetMapping(value = "/loginform")
	public String showLogin() {
		log.info("Going to login");
		return "LoginForm";
	}

	@GetMapping(value = "/registerform")
	public String showRegister() {
		log.info("Going to register");
		return "RegisterForm";
	}

	@GetMapping(value = "/LoginForm")
	public String showLoginSuccess() {
		log.info("Successful Login");
		return "LoginSuccess";
	}

	@GetMapping(value = "/taskform")
	public String showTask() {
		log.info("Going to task generator");
		return "TaskForm";
	}

	@GetMapping(value = "list")
	public String showListOfTasks() {
		log.info("Showing tasks");
		return "ListOfTasks";
	}

	@PostMapping(value = "/login")
	public String checkLogin(ModelMap m, @RequestParam String username, @RequestParam String password) {
		log.info("logging in...");
		if (userService.getPassword(username, password) == false || username.equals(" ") || password.equals(" "))  {
			log.info("login denied");
			return "denied";
		} else {
			log.info("login accepted");
			m.addAttribute("user", username);
			log.info("username: " + username);
			return "LoginSuccess";
		}
	}

	@PostMapping(value = "/register")
	public String registerUser(ModelMap m, @RequestParam String username, @RequestParam String email,
			@RequestParam String password) {
		if (username.equals(" ") || password.equals(" ") || email.equals(" ")) {
			return "denied";
		} else {
			log.info("Starting registration");

			User user = new User();
			user.setName(username);
			user.setEmail(email);
			user.setPassword(password);
			userService.save(user);
			m.addAttribute("user", user);
			return "RegisterSuccess";
		}
	}

	@GetMapping("/accessdenied")
	public String denied() {
		log.info("Access denied");
		return "denied";
	}

	@GetMapping(value = "/success")
	public String success() {

		return "LoginSuccess";
	}

	@RequestMapping(value = "/taskcreate")
	public String addTask(ModelMap m, @RequestParam String name, @RequestParam Date start, @RequestParam Date end,
			@RequestParam String desc, @RequestParam String sev, @RequestParam String username) {

		log.info("start add tasks..." + username);
		User user = userService.getUserByName(username);
		Task task = new Task();
		task.setName(name);
		task.setStart(start);
		task.setEnd(end);
		task.setDesc(desc);
		task.setSeverity(sev);
		task.setUser(user);
		m.addAttribute("task", task);
		m.addAttribute("user", username);
		taskService.save(task);

		return "TaskForm";
	}

}
