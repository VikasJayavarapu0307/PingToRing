package com.project.remainderSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.remainderSystem.DTO.RemainderRequest;
import com.project.remainderSystem.model.Reminder;
import com.project.remainderSystem.service.RemainderService;

@RestController
@RequestMapping("/api/reminder")
public class RemainderController {

	@Autowired
	private RemainderService remainderService;
	
	@PostMapping("/createReminder")
	public ResponseEntity<Reminder> scheduleReminder(@RequestBody RemainderRequest request) {
		Reminder savedReminder = remainderService.createRemainder(request);
		return ResponseEntity.ok(savedReminder);
	}
}
