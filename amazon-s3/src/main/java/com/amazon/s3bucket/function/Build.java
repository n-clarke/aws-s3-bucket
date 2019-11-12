package com.amazon.s3bucket.function;

import com.amazonaws.AmazonServiceException;

import java.util.Scanner;
import java.util.logging.Logger;

public class Build {

    public Build(){
        try {
            conn = new Connection(inScan.nextLine(), inScan.nextLine());
            bo = new BucketOperation(conn);
            oo = new ObjectOperation(conn);
        } catch (AmazonServiceException e) {
            log.info("Build Failed Please Try Again\n" + e.getErrorMessage());
            return;
        }
    }

    private Logger log = Logger.getLogger(Build.class.getName());
    private Scanner inScan = new Scanner(System.in);
    private Connection conn;
    public BucketOperation bo;
    public ObjectOperation oo;

    {
        inScan.close();
    }
}
