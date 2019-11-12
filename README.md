# AWS S3 Bucket Tool

This tool allows users to connect to Amazon Web Services and do the following actions to S3 Buckets.

Bucket Operations
```
- Create Buckets
- Delete Buckets
- View a List of All Buckets
```
Object Operations
```
- List all the available objects in S3 bucket
- Upload Files to Bucket
- Download Files from Bucket
- Copy Objects from one Bucket to another Bucket
- Delete Objects
```
Note* The Tool makes use of a Console Input UI.

## Prerequisites

AWS Access Keys (Access Key ID and Secret Access Key)
These access credentials are required to connect to Amazon S3. 
See documentation below:
```
https://docs.aws.amazon.com/general/latest/gr/aws-sec-cred-types.html
```
## Dependencies

junit-jupiter-api
```
https://search.maven.org/classic/#search%7Cga%7C1%7Cjunit-jupiter-api
```
junit-jupiter-engine
```
https://search.maven.org/classic/#search%7Cga%7C1%7Cjunit-jupiter-engine
```
aws-java-sdk-s3
```
https://search.maven.org/classic/#search%7Cga%7C1%7Ca%3A%22aws-java-sdk-s3%22
```
