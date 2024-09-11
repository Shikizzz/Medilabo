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

  
