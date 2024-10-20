# ArchitectureCloudIntegration

## Spring Boot applikation
Skapade en ny Spring Boot applikation i IntelliJ med hus och byggföretag. 
Jag använde mig av MySql som databas.
Jag skapade ett repo på GitHub. 
Ett byggföretag kan ha flera olika typer av hus. Hus har därför en ManyToOne koppling till byggföretag. 

### Endpoints
#### Company
#### GET /company - Hämtar alla byggföretag. 
Förväntar sig inga parametrar.Förväntad response: 200 OK

#### GET /company/{id} - Hämtar ett byggföretag med ett specifikt id. 
Förväntar sig ett id som path parameter. Förväntad response: 200 OK

#### POST /company - Skapar ett nytt byggföretag. 
Förväntar sig ett JSON-objekt med namn och adress. Förväntad response: 200 OK
    
    ```
    {
        "name": "Byggföretag AB",
        "location": "Byggvägen 1"
    }
    ```

#### PATCH /company/{id} - Uppdaterar ett byggföretag med ett specifikt id
Förväntar sig ett id som path parameter och ett JSON-objekt med namn och adress. Förväntad response: 200 OK

#### DELETE /company/{id} - Tar bort ett byggföretag med ett specifikt id
Förväntar sig ett id som path parameter. Förväntad response: 200 OK

#### House
#### GET /house - Hämtar alla hus
Förväntar sig inga parametrar. Förväntad response: 200 OK

#### GET /house/{id} - Hämtar ett hus med ett specifikt id
Förväntar sig ett id som path parameter. Förväntad response: 200 OK

#### POST /house - Skapar ett nytt hus
Förväntar sig ett JSON-objekt med namn, adress och companyId. Förväntad response: 200 OK

    ```
    {
        "name": "Skärdgårsvillan",
        "type": "Attefall",
        "size": 30, 
        "cost": 2000000,
        "readyMade": true,
        "company": {
            "id": 1
        }
    }
    ```
#### PATCH /house/{id} - Uppdaterar ett hus med ett specifikt id
#### DELETE /house/{id} - Tar bort ett hus med ett specifikt id

## GitHub Actions
Skapade en ny mapp i mitt repo som heter .github/workflows och skapade en ny fil som heter mavenBuild.yml
Fick fel på testerna men när jag uppdaterade tessterna så gick det igenom på Github actions

## Databas
skapade en rds databas på AWS. Mysql, free tier, public access yes. 
Gick sen in på security groups och på databasen som startats och valde att ändra inbound rule med att lägga till MySQL aurora till att vara öppen för alla. 
Lade sen in den DBBeaver för att testa kopplingen. 
Fick problem när skulle starta applikationen för att den inte hittade databsen. Då lade jag in i application properties att en databas skulle skapas om den inte fanns genom att lägga till ?createDatabaseIfNotExist=true efter databasnamnet.
Efter att ha skapat databasen så kunde jag starta applikationen och se att tabellerna skapades i databasen.
Då testade jag alla endpoints och det fungerade och lade in data i databasen.

## Elstic Beanstalk och EC2
EC2 kan man se som huset och RDS som rummet där man förvarar saker.
Skapade en ny applikation på AWS Elastic Beanstalk med inställningarna web serice enviroment, Managed platform som Java och Sample application.

Skapade domänen : Arch-env.eba-yt7en8uv.eu-north-1.elasticbeanstalk.com 
Gick in på domänen och såg att det fungerade.

## CodeBuild
CodeBuild bygger huset. Den exporterar en jarfil som sedan kan köras på EC2.
Kopplar ihop GitHub med CodeBuild.
Skapar webshook med SingleBuild och PUSH filter. 
Skapar buildspec med editor och fyller i med kod.
```
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

Fick succeeded på CodeBuild. 

## CodePipeline
CodePipeline är en pipeline som kopplar ihop CodeBuild och GitHub. Den kan ses som den som överseer hela husbygget. 
Genom detta skapas en CI/CD pipeline med automatisk bygge och deploy av applikationen.
Väljer new service Role och Github connection version1. Använder mig av Github webhooks för att upptäcka ändringar i mitt github repo. 

När alla stegen blivit gröna och deploy gått igenom kollar jag att jag kan hämta GET requests i webbläsaren på hemsidan som fick från Elastic Beanstalk.
Testar även att göra post requests via postman och det fungerar.
```
http://arch-env.eba-yt7en8uv.eu-north-1.elasticbeanstalk.com/house
```
## Frontend
Eftersom jag arbetat en del med React förut valde jag att göra frontend i React. Jag skapade en React app som ska utföra CRUD på alla hus och byggföretag.
Jag skapade även ett nytt repo för frontend på GitHub.
Jag stötte på Cors problem och lade in Cors config i backend för att lösa det.
```
@CrossOrigin(origins = "*")
```
I frontend har jag skapat CRUD på både hus och byggföretag.
Jag har skapat en .env som jag har lagt i .gitignore för api-länken till AWS. 


## Länkar och övrigt
Dokument med bilder är skickade på classroom i inlämningsfliken.
Inbjudan till repona har skickats till läraren.
Backend: https://github.com/bycaroline/ArchitectureCloudIntegration
Frontend: https://github.com/bycaroline/ArchitectureCloudIntegrationFronteEnd