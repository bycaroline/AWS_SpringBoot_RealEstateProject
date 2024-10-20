# ArchitectureCloudIntegration

## Spring Boot applikation
Skapade en ny Spring Boot applikation i IntelliJ med hus och byggföretag. 
Jag använde mig av MySql som databas. 
Ett byggföretag kan ha flera olika typer av hus. Hus har därför en ManyToOne koppling till byggföretag. 

### Endpoints
GET /company - Hämtar alla byggföretag
GET /company/{id} - Hämtar ett byggföretag med ett specifikt id
POST /company - Skapar ett nytt byggföretag
PATCH /company/{id} - Uppdaterar ett byggföretag med ett specifikt id
DELETE /company/{id} - Tar bort ett byggföretag med ett specifikt id

GET /house - Hämtar alla hus
GET /house/{id} - Hämtar ett hus med ett specifikt id
POST /house - Skapar ett nytt hus
PATCH /house/{id} - Uppdaterar ett hus med ett specifikt id
DELETE /house/{id} - Tar bort ett hus med ett specifikt id

## GitHub Actions
Skapade en ny mapp i mitt repo som heter .github/workflows och skapade en ny fil som heter mavenBuild.yml
Fick fel på testerna men när jag uppdaterade tessterna så gick det igenom på Github actions

## Databas
skapade en rds databas på AWS. Mysql, free tier, public access yes. 
Gick sen in på security groups och på databasen som startats och valde att ändra inbound rule med att lägga till MySQL aurora till att vara öppen för alla. 
Lade sen in den DBBeaver för att testa kopplingen. 
