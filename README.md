# Medilabo
 
This project is an exercice for an Openclassrooms formation.

It's a medical application with Microservices architecture.
Here are the modules you will find :
- Front, a simple and sober user interface using Thymeleaf
- Gateway, that forwards request from the front to the appropriate backend microservice, using the Spring Cloud Gateway MVC api.
   -> The security is implemented in the Gateway and uses JWT token for authentication.
- Info, a backend microservice using MySql to manage patient's informations.
- Notes, a backend microservice using MongoDB to store notes taken by Doctors about patients.
- Risk, a backend microservice that evaluates diabetes risk of the patient, using informations from hte Info and the Notes microservices.

  
# How to run the project on docker :

First, you need Docker installed on your machine.
If it's not the case, you can follow this guide : https://docs.docker.com/get-started/get-docker/


1) Build the docker images

Inside a Terminal, use these commands to create the MySQL and MongoDB images :
docker pull mysql 
docker pull mongodb/mongodb-community-server:latest

Then you need to fork the repository, and build the JAR file for the five microservices. 
For each microservice, you have to go to the microservice path on your machine, and enter the following command lines :
mvn -DskipTests=true package     
docker build -f Dockerfile -t {microservice_name} .


2) Use Docker-Compose to run all the containers

Use a Terminal, and go to the gateway path, and enter the command line :
docker compose up   

Some containers may fail to run, because mysql service is too long to be operational. Just start them again when Mysql is listening.


CONGRATULATIONS, the application is running.
You can navigate it on your browser, at the URL http://localhost:8082
You can login with "user" and "password" as credentials

# Green code ideas

- First, we need to note that the application is quite light :
   Front-end is mminimal, no animations, no pictures, no videos, etc.
   Back-end has no complex algorithm that could be long to process (no loops).
   Java is one of the most energy-efficient languages.

- NoSQL databases requires less processing power than relational ones, so we could switch our MySQL databases to MongoDB, even if it's not the best solution in term of programmation logic.

- Chosing a good deployment option. A deployment on the cloud is very efficient. In that case, it could be interresting to choose a cloud provider that is respective for environment.

- About security, I could have duplicated the code that checks JTW tokens in each backend microservice. Instead, securing the backends architecturally (with firewalls) prevents from making too many database calls.
