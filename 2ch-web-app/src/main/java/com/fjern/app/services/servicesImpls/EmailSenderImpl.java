package com.fjern.app.services.servicesImpls;

import com.fjern.app.services.EmailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSenderImpl implements EmailSender {
    @Override
    public void sendVerificationUrl(String verificationUrl, String email) {
        System.out.println(verificationUrl);
    }
}
