package com.amazon.s3bucket.function;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import java.util.logging.Logger;

public class Connection
{
    Connection(String accessKey, String secretKey)
    {
        try {
            setAWSCredentials(accessKey, secretKey);
        } catch (AmazonServiceException e) {
            log.info("Connection to S3Bucket has Failed Please Try Again\n" + e.getErrorMessage());
            return;
        }
    }

    Connection(String accessKey, String secretKey, Regions region){
        try {
            setAWSCredentials(accessKey, secretKey);
            setRegion(region);
        } catch (AmazonServiceException e) {
            log.info("Connection to S3Bucket has Failed Please Try Again\n" + e.getErrorMessage());
            return;
        }
    }

    private Logger log = Logger.getLogger(Connection.class.getName());
    private AWSCredentials credentials;
    private Regions getRegion = Regions.DEFAULT_REGION;

    private void setAWSCredentials(String accessKey, String secretKey)
    {
        try {
            credentials = new BasicAWSCredentials(accessKey, secretKey);
            log.info("Credentials have successfully been set.");
        } catch (AmazonServiceException e) {
            log.info(e.getErrorMessage());
            return;
        }
    }

    private void setRegion (Regions region) { this.getRegion = region; }

    AmazonS3 s3client = AmazonS3ClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(getRegion)
            .build();
}
