**TASK 1**

**To run the applications execute in Command Prompt in the root path of task1:**
 
docker compose up


**To stop the applications execute in Command Prompt:**

Ctrl + c


**Applications run on:**

- http://localhost:8081/swagger-ui/index.html
- http://localhost:8082/swagger-ui/index.html


**Scrips used in task1 to build, push to docker hub (https://hub.docker.com/), and run the applications:**

- docker system prune -a
- docker volume prune
- cd post-service
- mvn clean install
- docker build -t eds832/postapplication:2.0.0 ./
- login to docker: docker login -u your_login -p your_password
- docker push eds832/postapplication:2.0.0
- cd ./../user-service
- mvn clean install
- docker build -t eds832/userapplication:2.0.0 ./
- docker push eds832/userapplication:2.0.0
- cd ./../


**Task 1 discription:**

https://git.epam.com/Siarhei_Svila/kubernetes-mentoring-program/-/blob/main/1-microservices-architecture-and-docker/task/README.md
