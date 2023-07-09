package br.com.integrator.demo.services;

import br.com.integrator.demo.configs.AwsSqsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class PatientSender {


    @Autowired
    private AwsSqsConfig aws;

    public void send(String patientName) {
        this.aws.getTemplate().send(AwsSqsConfig.queueName, MessageBuilder.withPayload(patientName).build());
    }
}
