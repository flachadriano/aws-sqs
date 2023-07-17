package br.com.integrator.demo.configs;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsSqsConfig {

    private static final String accessKey = "AKIAX52TIWXLIIYIJBLP";
    private static final String secretKey = "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";
    public static final String queueAddress = "https://sqs.us-east-1.amazonaws.com/545098610134/";
    public static final String queueName = "PatientIntegration";

    @Bean
    public QueueMessagingTemplate getTemplate() {
        return new QueueMessagingTemplate(getClient());
    }

    public AmazonSQSAsync getClient() {
        return AmazonSQSAsyncClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();
    }
}
