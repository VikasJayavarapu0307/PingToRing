package com.project.remainderSystem.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Reminder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String phoneNumber;
	private String message;
    private LocalDateTime scheduledTime;
    private String status; //"PENDING", "COMPLETED", "FAILED"
    
    
    
    public Reminder(String phoneNumber, String message, LocalDateTime scheduledTime) {
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.scheduledTime = scheduledTime;
        this.status = "PENDING";
    }
}
