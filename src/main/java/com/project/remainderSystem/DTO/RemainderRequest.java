package com.project.remainderSystem.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RemainderRequest {
	private String phoneNumber;
    private String message;
    private LocalDateTime scheduledTime;
}
