package com.fjern.app.services;

import org.springframework.stereotype.Component;

@Component
public interface EmailSender {
    void sendVerificationUrl(String verificationUrl, String email);
}
