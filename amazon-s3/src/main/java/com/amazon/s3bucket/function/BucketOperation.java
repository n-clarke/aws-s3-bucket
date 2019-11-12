package com.amazon.s3bucket.function;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.Bucket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class BucketOperation
{
    BucketOperation(Connection conn)
    {
        this.conn = conn;
    }

    private Connection conn;
    private Logger log = Logger.getLogger(BucketOperation.class.getName());

    //Amazon S3 Bucket Operations
    private ArrayList bucketNamesList = null;

    public String getAllBucketNames()
    {
        setAllBucketsNames();
        return Arrays.toString(new ArrayList[]{ this.bucketNamesList });
    }

    private void setAllBucketsNames()
    {
        List<Bucket> buckets = conn.s3client.listBuckets();
        for(Bucket bucket : buckets) bucketNamesList.add(bucket.getName());
    }

    public void createBucket(String bucketName)
    {
        if(conn.s3client.doesBucketExistV2(bucketName))
        {
            log.info("Bucket name is not available. \nTry again with a different Bucket name.");
            return;
        }
        conn.s3client.createBucket(bucketName);
        log.info("Bucket: "+ bucketName +" has been created successfully");
    }

    public void deleteBucket(String bucketName)
    {
        try {
            conn.s3client.deleteBucket(bucketName);
        } catch (AmazonServiceException e) {
            log.info(e.getErrorMessage());
            return;
        }
    }
}
