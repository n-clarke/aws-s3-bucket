package com.amazon.s3bucket.build;

import com.amazonaws.regions.Regions;
import com.amazon.s3bucket.function.Build;

public class Main
{
    private final Regions clientRegion = Regions.DEFAULT_REGION;
    private final String bucketName = "*** Bucket name ***";
    private String key = "*** Object key ***";

    public static void main(String[] args)
    {
        Build b = new Build();
        b.createBucket("thisismybucket");

    }
}
