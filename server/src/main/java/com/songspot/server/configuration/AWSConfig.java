package com.songspot.server.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "aws")
public class AWSConfig {

    private static final String ACCESS_KEY = "AKIAZQ5SZ3JK5ND6XFX5";
    private static final String SECRET_KEY = "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";
    private String endpointUrl;
    private String bucketName;

    public AWSConfig() {
    }

    public AWSConfig(String endpointUrl, String bucketName) {
        this.endpointUrl = endpointUrl;
        this.bucketName = bucketName;
    }

    public String getEndpointUrl() {
        return endpointUrl;
    }

    public void setEndpointUrl(String endpointUrl) {
        this.endpointUrl = endpointUrl;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getAwsSecretKey() {
        return System.getenv(SECRET_KEY);
    }

    public String getAWSAccessKey() {
        return System.getenv(ACCESS_KEY);
    }
}