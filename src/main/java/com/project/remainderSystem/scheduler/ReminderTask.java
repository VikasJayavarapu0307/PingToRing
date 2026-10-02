package com.project.remainderSystem.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.project.remainderSystem.model.Reminder;
import com.project.remainderSystem.repository.ReminderRepository;
import com.project.remainderSystem.service.TwilioCallService;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ReminderTask {

    private static final Logger log = LoggerFactory.getLogger(ReminderTask.class);

    @Autowired
    private ReminderRepository reminderRepository;

    @Autowired
    private TwilioCallService twilioCallService;

    // Runs every 1 minute
    @Scheduled(fixedRate = 60000)
    public void processDueReminders() {
        LocalDateTime now = LocalDateTime.now();
        
        List<Reminder> dueReminders = reminderRepository
            .findByStatusAndScheduledTimeLessThanEqual("PENDING", now);

        if (!dueReminders.isEmpty()) {
            log.info("Found {} due reminder(s) to process.", dueReminders.size());
        }

        for (Reminder reminder : dueReminders) {
            try {
                log.info("Calling {} for reminder ID {}", reminder.getPhoneNumber(), reminder.getId());
                
                // Trigger Phase 1 of the Twilio flow
                twilioCallService.makeCall(reminder.getPhoneNumber(), reminder.getId());
                
                reminder.setStatus("COMPLETED");
                reminderRepository.save(reminder);
                
            } catch (Exception e) {
                log.error("Failed to make call for reminder ID {}", reminder.getId(), e);
                
                reminder.setStatus("FAILED");
                reminderRepository.save(reminder);
            }
        }
    }
}