package com.amazon.s3bucket.function;

import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class ObjectOperation {

    ObjectOperation(Connection conn)
    {
        this.conn = conn;
    }

    private Connection conn;
    private Logger log = Logger.getLogger(ObjectOperation.class.getName());

    //Uploads the specified file to Amazon S3 under the specified bucket and key name.
    void uploadObject(String bucketName, String key, String file){
        conn.s3client.putObject(
                bucketName,
                key,
                new File(file)
        );
    }

    //list all the available objects in our S3 bucket
    void listObjects(String bucketName){
        ObjectListing objectListing = conn.s3client.listObjects(bucketName);
        for(S3ObjectSummary os : objectListing.getObjectSummaries()) {
            log.info(os.getKey());
        }
    }

    void downloadObject(String bucketName, String key, String saveLocalFileCopyUrl ){
        try {
            S3Object s3object = conn.s3client.getObject(bucketName, key);
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
        conn.s3client.copyObject(srcBucketName, srcKey, destBucketName, destKey);
    }

    void deleteObject(String bucketName, String objKey)
    {
        conn.s3client.deleteObject(bucketName, objKey);
    }

    void deleteObject(String bucketName, String[] objKeyArr)
    {
        DeleteObjectsRequest delObjReq = new DeleteObjectsRequest(bucketName)
                .withKeys(objKeyArr);
        conn.s3client.deleteObjects(delObjReq);
    }
}