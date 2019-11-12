package com.amazon.s3bucket.function;

import com.amazonaws.AmazonServiceException;

import java.util.Scanner;
import java.util.logging.Logger;

public class Build
{
    public Build()
    {
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
                    "MENU OPTIONS" +
                            "-------------------" +
                            "0 : System Exit." +
                            "1 : Main Menu" +
                            "2 : Create Bucket" +
                            "3 : Delete Bucket" +
                            "4 : View List of All Buckets" +
                            "-------------------" +
                            "Enter an option from the menu.");

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
                "MENU OPTIONS" +
                "-------------------" +
                "0 : System Exit." +
                "1 : Main Menu" +
                "2 : List all the available objects in S3 bucket" +
                "3 : Upload File to Bucket" +
                "4 : Download File from Bucket" +
                "5 : Copy Object to another Bucket" +
                "6 : Delete Object" +
                "-------------------" +
                "Enter an option from the menu.");

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
                        System.out.println("Please Enter The Bucket Name You Want To Retrieve The Data From.");
                        oo.listObjects(scan.nextLine());
                        break;
                    case 3:
                        System.out.println("Please Enter The Bucket Name You Want To Retrieve The Data From."
                                + "Enter Your Buckets Key."
                                + "Enter The Path You Would Like The File To Be Uploaded.");
                        oo.uploadObject(scan.nextLine(), scan.nextLine(), scan.nextLine());
                        break;
                    case 4:
                        System.out.println("Please Enter The Bucket Name You Want To Retrieve The Data From."
                                + "Enter Your Buckets Key."
                                + "Enter The Path You Would Like The File To Be Downloaded.");
                        oo.downloadObject(scan.nextLine(), scan.nextLine(), scan.nextLine());
                        break;
                    case 5:
                        System.out.println("Please Enter The Bucket Name You Want To Retrieve The Data From."
                                + "Enter Your Buckets Key."
                                + "Please Enter The Bucket Name You Want To Send The Data To."
                                + "Enter Your Buckets Key.");
                        oo.copyObjectToBucket(scan.nextLine(), scan.nextLine(), scan.nextLine(), scan.nextLine());
                    case 6:
                        System.out.println("Please Enter The Bucket Name You Want To Delete The Data From."
                                        + "Enter Your Buckets Key.");
                        oo.deleteObject(scan.nextLine(), scan.nextLine());
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
}
