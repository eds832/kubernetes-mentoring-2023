## Sub-task 1: Secrets and config-maps

1. Add Secrets object to your k8s manifest to store database username and password.
2. Add config maps to store environment variables for application deployments.
3. Add sql scripts to init databases (create tables) to config maps.
4. Change k8s Deployment and StatefulSet objects to load these secrets and config-maps.

**Scrips used to update and run the applications**

- docker system prune -a
- docker volume prune
- cd ./task1/post-service
- mvn clean install
- docker build -t eds832/postapplication:3.0.0 ./
- login to docker: docker login -u your_login -p your_password
- docker push eds832/postapplication:3.0.0
- cd ./../user-service
- mvn clean install
- docker build -t eds832/userapplication:3.0.0 ./
- docker push eds832/userapplication:3.0.0
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