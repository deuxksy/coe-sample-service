package org.coe.customer.message;

import org.coe.customer.config.MessageQueueConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    private Mailer mailer;

    @Autowired
    public Receiver(Mailer mailer) {
        this.mailer = mailer;
    }

    @RabbitListener(queues = MessageQueueConfig.QUEUE_NAME)
    public void processMessage(String email) {
        System.out.println("get Message:" + email);
        mailer.sendMail(email);
    }
}
