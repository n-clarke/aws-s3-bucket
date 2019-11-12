package com.amazon.s3bucket.build;

import com.amazon.s3bucket.function.Build;

public class Main
{
    public static void main(String[] args)
    {
        Build b = new Build();
        b.bo.createBucket("thisismybucket");
        b.oo.uploadObject("thisismybucket", "key", "helloworld.tx");
        b.oo.listObjects("thisismybucket");
    }
}
