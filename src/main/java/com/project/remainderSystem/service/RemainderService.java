package com.project.remainderSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.remainderSystem.DTO.RemainderRequest;
import com.project.remainderSystem.model.Reminder;
import com.project.remainderSystem.repository.ReminderRepository;

@Service
public class RemainderService {

	@Autowired
	private ReminderRepository reminderRepo;
	
	public Reminder createRemainder(RemainderRequest request) {
		Reminder reminder = new Reminder(
				request.getPhoneNumber(),
				request.getMessage(),
				request.getScheduledTime()
				);
		return reminderRepo.save(reminder);
	}

}
