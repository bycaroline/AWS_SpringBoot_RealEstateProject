# AWS Cloud Integration Sring Boot Project for Real Estate

## Overview

This project is a Spring Boot application that manages construction companies and houses, using MySQL as the database. The application allows CRUD operations for companies and houses, and is hosted on AWS using EC2 and RDS.

### Database

* **MySQL**: The database is hosted on AWS RDS with public access enabled. In case the database does not exist, it is automatically created when the application starts using the `createDatabaseIfNotExist=true` property in `application.properties`.

### Endpoints

#### Company Endpoints

* **GET /company**: Fetches all construction companies. No parameters required. Expected response: `200 OK`.
* **GET /company/{id}**: Fetches a specific construction company by ID. Requires an `id` as a path parameter. Expected response: `200 OK`.
* **POST /company**: Creates a new construction company. Expects a JSON object with `name` and `location`. Expected response: `200 OK`.

```json
{
  "name": "Construction Company AB",
  "location": "Building Street 1"
}
```

* **PATCH /company/{id}**: Updates a construction company by ID. Requires an `id` as a path parameter and a JSON object with `name` and `location`. Expected response: `200 OK`.
* **DELETE /company/{id}**: Deletes a construction company by ID. Requires an `id` as a path parameter. Expected response: `200 OK`.

#### House Endpoints

* **GET /house**: Fetches all houses. No parameters required. Expected response: `200 OK`.
* **GET /house/{id}**: Fetches a specific house by ID. Requires an `id` as a path parameter. Expected response: `200 OK`.
* **POST /house**: Creates a new house. Expects a JSON object with `name`, `type`, `size`, `cost`, `readyMade`, and `companyId`. Expected response: `200 OK`.

```json
{
  "name": "Archipelago Villa",
  "type": "Attefall",
  "size": 30,
  "cost": 2000000,
  "readyMade": true,
  "company": {
    "id": 1
  }
}
```

* **PATCH /house/{id}**: Updates a house by ID.
* **DELETE /house/{id}**: Deletes a house by ID.

### GitHub Actions

I created a new folder in my repository called `.github/workflows` and added a file named `mavenBuild.yml`. After facing issues with the tests, I updated them, and the tests successfully passed on GitHub Actions.

### AWS Setup

1. **RDS Database**: I created a MySQL database on AWS RDS (free tier) with public access enabled. I updated the inbound rules to allow MySQL Aurora access from any source. The database was tested using DBBeaver, and after creating it, I was able to start the application and verify that the tables were created correctly.

2. **Elastic Beanstalk & EC2**: I deployed the application on AWS Elastic Beanstalk, using a web service environment with a managed platform for Java. The application is accessible via the domain: `Arch-env.eba-yt7en8uv.eu-north-1.elasticbeanstalk.com`.

3. **CodeBuild**: I connected CodeBuild with GitHub to build a JAR file. The build process included the following `buildspec.yml`:

```yaml
version: 0.2

phases:
  install:
    runtime-versions:
      java: corretto21
  pre_build:
    commands:
      - echo Nothing to do in the pre_build phase...
  build:
    commands:
      - echo Build started on date
      - mvn install
      - mvn clean package
  post_build:
    commands:
      - echo Build completed on date
artifacts:
  files:
    - '**/*'
  discard-paths: yes
```

4. **CodePipeline**: I used CodePipeline to link CodeBuild with GitHub, creating a CI/CD pipeline for automatic build and deployment. After the pipeline completed successfully, I tested the application by making GET and POST requests using Postman and verified the responses on the website.

   Endpoint:`http://arch-env.eba-yt7en8uv.eu-north-1.elasticbeanstalk.com/house`

### Frontend

For the frontend, I created a React application to perform CRUD operations on houses and construction companies. The frontend is hosted on a separate GitHub repository. To resolve CORS issues, I configured the backend to allow cross-origin requests with:

```java
@CrossOrigin(origins = "*")
```

### Repositories

* **Backend**: [GitHub Repository (Backend)](https://github.com/bycaroline/ArchitectureCloudIntegration)
* **Frontend**: [GitHub Repository (Frontend)](https://github.com/bycaroline/ArchitectureCloudIntegrationFronteEnd)

---

This structure organizes the information and uses appropriate terminology for GitHub documentation. Let me know if you need any further adjustments!
