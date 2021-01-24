package com.restfulwebservices.restfulwebservices.dynamoExample.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamodbConfig {

    public static final String SERVICE_ENDPOINT = "dynamodb.us-west-1.amazonaws.com";
    public static final String REGION = "us-west-1";
    public static final String ACCESS_KEY = "";
    public static final String SECRET_KEY = "";

    @Bean
    public DynamoDBMapper mapper()
    {
        return new DynamoDBMapper(amazonDynamodbConfig());
    }

    private AmazonDynamoDB amazonDynamodbConfig() {
        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(SERVICE_ENDPOINT,REGION))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY))).build();
    }
}

