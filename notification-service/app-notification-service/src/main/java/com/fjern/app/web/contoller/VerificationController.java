package com.fjern.app.web.contoller;

import com.fjern.app.persistence.entity.dto.VerificationRequest;
import com.fjern.app.web.util.UtilMappings;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UtilMappings.Plural.VERIFICATIONS)
public class VerificationController {

    @RequestMapping(UtilMappings.SEND)
    @ResponseStatus(HttpStatus.OK)
    public void send (@RequestBody VerificationRequest verificationRequest) {
        System.out.println(verificationRequest.getVerificationUrl());
    }
}
