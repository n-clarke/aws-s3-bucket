package com.amazon.s3bucket.function;

import com.amazonaws.AmazonServiceException;

import java.util.Scanner;
import java.util.logging.Logger;

public class Build
{
    public Build()
    {
        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("Please enter your accessKey\n" + "Please enter your secretKey");
            conn = new Connection(scan.nextLine(), scan.nextLine());
            bo = new BucketOperation(conn);
            oo = new ObjectOperation(conn);
        } catch (AmazonServiceException e) {
            log.info("Build Failed Please Try Again\n" + e.getErrorMessage());
            return;
        }
        scan.close();
    }

    private Logger log = Logger.getLogger(Build.class.getName());
    private Connection conn;
    public BucketOperation bo;
    public ObjectOperation oo;

    public void displayMainMenu() {
        Scanner scan = new Scanner(System.in);
        try {
            boolean run = true;

            System.out.println(
                "MENU OPTIONS" +
                "-------------------" +
                "0 : System Exit." +
                "1 : Bucket Operations" +
                "2 : Object Operations" +
                "-------------------" +
                "Enter an option from the menu.");

            while (run == true)
            {
                switch (scan.nextInt()) {
                    case 0:
                        System.exit(0);
                    case 1:
                        displayBucketOperationMenu();
                        break;
                    case 2:
                        displayObjectOperationMenu();
                        break;
                    default:
                        System.out.println("Selection is not on menu, please try again or enter 0 to exit program.");
                        displayMainMenu();
                        break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        scan.close();
    }

    public void displayBucketOperationMenu()
    {
        Scanner scan = new Scanner(System.in);
        try {
            boolean run = true;

            System.out.println(
                "MENU OPTIONS\n" +
                "-------------------\n" +
                "0 : System Exit.\n" +
                "1 : Main Menu\n" +
                "2 : Create Bucket\n" +
                "3 : Delete Bucket\n" +
                "4 : View List of All Buckets\n" +
                "-------------------\n" +
                "Enter an option from the menu.\n");

            while (run == true)
            {
                switch (scan.nextInt())
                {
                    case 0:
                        System.exit(0);
                    case 1:
                        run = false;
                        displayMainMenu();
                        break;
                    case 2:
                        System.out.println("Enter Bucket Name:");
                        bo.createBucket(scan.nextLine());
                        break;
                    case 3:
                        System.out.println("Enter Bucket Name:");
                        bo.deleteBucket(scan.nextLine());
                        break;
                    case 4:
                        bo.getAllBucketNames();
                        break;
                    default:
                        System.out.println("Selection is not on menu, please try again or enter 0 to exit program.");
                        displayBucketOperationMenu();
                        break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        scan.close();
    }

    public void displayObjectOperationMenu()
    {
        Scanner scan = new Scanner(System.in);
        try {
            boolean run = true;

            System.out.println(
                "MENU OPTIONS\n" +
                "-------------------\n" +
                "0 : System Exit.\n" +
                "1 : Main Menu\n" +
                "2 : List all the available objects in S3 bucket\n" +
                "3 : Upload File to Bucket\n" +
                "4 : Download File from Bucket\n" +
                "5 : Copy Object to another Bucket\n" +
                "6 : Delete Object\n" +
                "-------------------\n" +
                "Enter an option from the menu.\n");

            while (run == true)
            {
                switch (scan.nextInt())
                {
                    case 0:
                        System.exit(0);
                    case 1:
                        run = false;
                        displayMainMenu();
                        break;
                    case 2:
                        System.out.println("Please Enter The Bucket Name You Want To Retrieve The Data From.\n");
                        oo.listObjects(scan.nextLine());
                        break;
                    case 3:
                        System.out.println("Please Enter The Bucket Name You Want To Retrieve The Data From.\n"
                                + "Enter Your Buckets Key.\n"
                                + "Enter The Path You Would Like The File To Be Uploaded.\n");
                        oo.uploadObject(scan.nextLine(), scan.nextLine(), scan.nextLine());
                        break;
                    case 4:
                        System.out.println("Please Enter The Bucket Name You Want To Retrieve The Data From.\n"
                                + "Enter Your Buckets Key.\n"
                                + "Enter The Path You Would Like The File To Be Downloaded.\n");
                        oo.downloadObject(scan.nextLine(), scan.nextLine(), scan.nextLine());
                        break;
                    case 5:
                        System.out.println("Please Enter The Bucket Name You Want To Retrieve The Data From.\n"
                                + "Enter Your Buckets Key.\n"
                                + "Please Enter The Bucket Name You Want To Send The Data To.\n"
                                + "Enter Your Buckets Key.\n");
                        oo.copyObjectToBucket(scan.nextLine(), scan.nextLine(), scan.nextLine(), scan.nextLine());
                    case 6:
                        System.out.println("Please Enter The Bucket Name You Want To Delete The Data From.\n"
                                        + "Enter Your Buckets Key.\n");
                        oo.deleteObject(scan.nextLine(), scan.nextLine());
                    default:
                        System.out.println("Selection is not on menu, please try again or enter 0 to exit program.\n");
                        displayBucketOperationMenu();
                        break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        scan.close();
    }
}
