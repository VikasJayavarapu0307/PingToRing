package com.project.remainderSystem.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.remainderSystem.model.Reminder;
import com.project.remainderSystem.repository.ReminderRepository;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Say;

@RestController
@RequestMapping("/api/webhook/twilio")
public class WebhookController {

	@Autowired
   private ReminderRepository reminderRepository;
	
	@PostMapping(value = "/reminder/{id}", produces = "application/xml")
    public String handleReminderCall(@PathVariable Long id) {
		Optional<Reminder> reminderOpt = reminderRepository.findById(id);
		String messageToSay = "Hello, you have a reminder."; // Fallback message
		if (reminderOpt.isPresent()) {
            messageToSay = reminderOpt.get().getMessage();
        }

        // Build the TwiML instructions
        Say say = new Say.Builder(messageToSay).build();
        VoiceResponse response = new VoiceResponse.Builder()
            .say(say)
            .build();

        // Return the XML string back to Twilio
        return response.toXml();
	}
}