## Sub-task 2: Liveness and Readiness probes
1. Add endpoints for health checks at your applications.
2. Add startup, liveness and readiness probes for your Deployment objects at k8s manifest.
3. Add startup, liveness and readiness probes for your StatefulSet objects at k8s manifest.


**Scrips used to update and run the applications**

- docker system prune -a
- docker volume prune
- cd ./task1/post-service
- mvn clean install
- docker build -t eds832/postapplication:4.0.0 ./
- login to docker: docker login -u your_login -p your_password
- docker push eds832/postapplication:4.0.0
- cd ./../user-service
- mvn clean install
- docker build -t eds832/userapplication:4.0.0 ./
- docker push eds832/userapplication:4.0.0
- cd ../..
- kubectl apply -f ./
- kubectl apply -f ./secrets
- kubectl apply -f ./config
- kubectl apply -f ./databases
- kubectl apply -f ./user
- kubectl apply -f ./post
- kubectl get all -n=k8s-program
- kubectl delete all --all -n=k8s-program

**Applications run on:**

- http://localhost:30083/swagger-ui/index.html
- http://localhost:30084/swagger-ui/index.html
- http://localhost:30083/actuator/health/
- http://localhost:30084/actuator/health/