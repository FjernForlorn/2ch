package com.fjern.app.web.listeners;

import com.fjern.app.persistence.entities.Post;
import com.fjern.app.web.events.NewPostCreatedEvent;
import com.fjern.app.web.messaging.RabbitMqMessageProducer;
import com.fjern.app.web.messaging.configs.NotificationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class NewPostCreatedEventListener implements ApplicationListener<NewPostCreatedEvent> {

    @Autowired
    private RabbitMqMessageProducer messageProducer;

    @Autowired
    private NotificationConfig notificationConfig;

    @Override
    public void onApplicationEvent(NewPostCreatedEvent event) {
        Post newPost = event.getPost();
        messageProducer.publish(
                newPost,
                notificationConfig.getInternalExchange(),
                notificationConfig.getInternalNotificationRoutingKey());
    }
}
