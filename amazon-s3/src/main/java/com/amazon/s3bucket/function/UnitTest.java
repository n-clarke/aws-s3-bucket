package com.amazon.s3bucket.function;

import org.junit.jupiter.api.*;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Performing a Unit Test For")
public class UnitTest {

    private BucketOperation bo;
    //Note * this bucket name will need to be changed if it already exists on the AWS server.
    private String testBucketName = "thisisatestbucket12839y213y90";
    //Note * the access key and secret must be set however do not save your keys to this file
    private static String accessKey = "< access key>", secretKey = "< secret key>";

    @BeforeAll
    public static void init() {
        Scanner inScan = new Scanner(System.in);
        accessKey = inScan.nextLine();
        secretKey = inScan.nextLine();
        inScan.close();
    }

    @BeforeEach
    void initEach() {
        Connection conn = new Connection(accessKey, secretKey);
        bo = new BucketOperation(conn);
    }

    @Nested
    @DisplayName("Bucket Operations")
    class BucketOperationsTest {

        @Test
        @DisplayName("Create Bucket and Test if it Exists")
        @Tag("Bucket Operation")
        void createBucketNames() {
            bo.createBucket(testBucketName);
            String expected = testBucketName, actual = null;

            if(bo.getAllBucketNames().contains(testBucketName))
            {
                actual = testBucketName;
            }
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Delete Bucket")
        @Tag("Bucket Operation")
        void deleteBucket() {
            bo.deleteBucket(testBucketName);
            String expected = null, actual = null;

            if(bo.getAllBucketNames().contains(testBucketName))
            {
                actual = testBucketName;
            }
            assertEquals(expected, actual);
        }
    }

    @Nested
    @DisplayName("Object Operations")
    class ObjectOperationsTest {

        
    }
}
