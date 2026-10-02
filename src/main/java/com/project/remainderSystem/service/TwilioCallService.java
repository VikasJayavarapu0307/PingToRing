package com.project.remainderSystem.service;

import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioCallService {

    @Value("${twilio.phone-number}")
    private String fromNumber;

    @Value("${app.webhook-base-url}") 
    private String webhookBaseUrl;

    public void makeCall(String toPhoneNumber, Long reminderId) {
        String webhookUrl = webhookBaseUrl + "/api/webhook/twilio/reminder/" + reminderId;

        Call.creator(
            new PhoneNumber(toPhoneNumber),
            new PhoneNumber(fromNumber),
            URI.create(webhookUrl)
        ).create();
    }
}
