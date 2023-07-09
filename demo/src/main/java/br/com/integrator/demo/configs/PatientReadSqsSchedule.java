package br.com.integrator.demo.configs;

import com.amazonaws.services.sqs.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PatientReadSqsSchedule {

    @Autowired
    private AwsSqsConfig sqs;

    @Scheduled(fixedDelay = 10000, initialDelay = 300)
    public void read() {
        System.out.println("started");
        List<Message> messages = this.sqs.getClient().receiveMessage(AwsSqsConfig.queueAddress + AwsSqsConfig.queueName).getMessages();
        for (Message message : messages) {
            System.out.println(message.getBody());
            this.sqs.getClient().deleteMessage(AwsSqsConfig.queueAddress + AwsSqsConfig.queueName, message.getReceiptHandle());
        }
    }
}
