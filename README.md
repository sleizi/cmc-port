# CMC-PORT

CMC-PORT is a Java application to read and save ports data into the database from a large JSON file.
The application is built with Spring Boot, Maven and H2 database.
## Installation
You will need maven and docker preinstalled. 

Step 1: Download the repository from Github, open a terminal/cmd and go to the cmc-port folder and package the project by maven.

```bash
mvn package
```
Step 2: Build the docker image from the packaged jar file.
```bash
docker image build -t docker-java-jar:latest .  
```
Step 3: Run the Docker Container with Bind Mount. This will mount the directory where you store the ports.json file from your local computer into a directory inside the container
```bash
docker run -v /Users/ps1.tunx/Documents:/app/data -p 8080:8080 docker-java-jar:latest
```

## Usage

Copy the ports.json file to the mounted directory.
In my case: 
```
/Users/ps1.tunx/Documents/ports.json
```
When you want the application to read and process the JSON file, just open your browser and access this endpoint to trigger the API: 
```
http://localhost:8080/ports/import
```
You can edit the JSON file and trigger the API again to  update the changes into the database.

You can see the changes in the H2 database by navigating to: 
```
http://localhost:8080/h2-console
```
, which will present us with a login page.
You can login with parameters defined in application.properties file:
```
url: jdbc:h2:mem:testdb
username: sa
password: password
```
<img width="442" alt="Screen Shot 2023-09-07 at 11 02 35" src="https://github.com/sleizi/cmc-port/assets/92270588/ff776aba-68ed-4e23-81dc-687a7845854c">
<img width="774" alt="Screen Shot 2023-09-07 at 11 03 52" src="https://github.com/sleizi/cmc-port/assets/92270588/33e207f0-4a3a-4397-bf1e-0a40ce7c27d4">