package com.company.eduplatform.service;

import com.company.eduplatform.model.ApplicationSecret;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

@Service
public class SecretsManagerService {

    private final SecretsManagerClient secretsManagerClient =
            SecretsManagerClient.builder()
                    .region(Region.US_EAST_1)
                    .build();

    public ApplicationSecret getSecret() {

        try {

            GetSecretValueRequest request = GetSecretValueRequest.builder()
                    .secretId("education-platform/prod/application")
                    .build();

            GetSecretValueResponse response =
                    secretsManagerClient.getSecretValue(request);

            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(
                    response.secretString(),
                    ApplicationSecret.class
            );

        } catch (Exception e) {
            throw new RuntimeException(
                    "Unable to retrieve secret from AWS Secrets Manager",
                    e
            );
        }
    }
}