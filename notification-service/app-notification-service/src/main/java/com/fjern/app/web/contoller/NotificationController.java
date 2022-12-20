package com.fjern.app.web.contoller;

import com.fjern.app.persistence.entity.dto.NotificationRequest;
import com.fjern.app.web.util.UtilMappings;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UtilMappings.Plural.NOTIFICATIONS)
public class NotificationController {

    @RequestMapping(UtilMappings.SEND)
    @ResponseStatus(HttpStatus.OK)
    public void send (@RequestBody NotificationRequest notificationRequest) {
        System.out.println(notificationRequest.getText());
    }
}
