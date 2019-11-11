package com.amazon.s3bucket.functions;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

public class Bucket {

    private Logger log = Logger.getLogger(Bucket.class.getName());

    //Creating Client Connection
    private AWSCredentials credentials = new BasicAWSCredentials(
            "<AWS accesskey>",
            "<AWS secretkey>"
    );

    AmazonS3 s3client = AmazonS3ClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(Regions.DEFAULT_REGION)
            .build();

    //Amazon S3 Bucket Operations
    void createBucket(String bucketName)
    {
        if(s3client.doesBucketExistV2(bucketName))
        {
            log.info("Bucket name is not available."
                    + " Try again with a different Bucket name.");
            return;
        }
        s3client.createBucket(bucketName);
    }

    void viewAllBuckets()
    {
        List<com.amazonaws.services.s3.model.Bucket> buckets = s3client.listBuckets();
        for(com.amazonaws.services.s3.model.Bucket bucket : buckets)
        {
            System.out.println(bucket.getName());
        }
    }

    void deleteBucket(String bucketName)
    {
        try {
            s3client.deleteBucket(bucketName);
        } catch (AmazonServiceException e) {
            log.info(e.getErrorMessage());
            return;
        }
    }

    //Amazon S3 Object Operations

    //Uploads the specified file to Amazon S3 under the specified bucket and key name.
    void uploadObject(String bucketName, String key, String file){
        s3client.putObject(
                bucketName,
                key,
                new File(file)
        );
    }

    //list all the available objects in our S3 bucket
    void listObjects(String bucketName){
        ObjectListing objectListing = s3client.listObjects(bucketName);
        for(S3ObjectSummary os : objectListing.getObjectSummaries()) {
            log.info(os.getKey());
        }
    }

    void downloadObject(String bucketName, String key, String saveLocalFileCopyUrl ){
        try {
            S3Object s3object = s3client.getObject(bucketName, key);
            InputStream inputStream = s3object.getObjectContent();

            //Write to a new file at specified path
            Files.copy(inputStream, Paths.get(saveLocalFileCopyUrl));

            inputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    void copyObject(String srcBucketName, String srcKey, String destBucketName, String destKey)
    {
        s3client.copyObject(srcBucketName, srcKey, destBucketName, destKey);
    }

    void deleteObject(String bucketName, String objKey)
    {
        s3client.deleteObject(bucketName, objKey);
    }

    void deleteObject(String bucketName, String[] objkeyArr)
    {
        DeleteObjectsRequest delObjReq = new DeleteObjectsRequest(bucketName)
                .withKeys(objkeyArr);
        s3client.deleteObjects(delObjReq);
    }
}
