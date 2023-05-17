## Sub-task 2: Deploy containers in k8s

In this subtask you need to create manifest `.yml` files with configuration for deployment. These files should contain the next objects:
- Namespace (f.e. k8s-program). All other objects will use this namespace;
- 2 Services (one for each service of your system). Use NodePort service type and configure nodePort field for testing.
- 2 Deployments (one for each service of your system). For apps deployments set `replicas: 2`. You should add environment variables for your applications here.

_Note_: don't forget to specify namespace all objects. <br>
Delete EXPOSE instruction from dockerfiles and upgrade images. <br>
To deploy, run `kubectl apply ./` in folders where yml files are stored.
To view all objects run `kubectl get all -n=<your_namespace>`. <br>
Along with services and deployments, this command outputs pods and replica-sets. **Find out why.**

## Sub-task 3: Persistent volumes

In this subtask you will make your app pods use local storage. This will ensure that no data is lost during pod deploy/redeploy.
1. Add PersistentVolume object with "manual" storage class for the User service (create separate manifest file). Configure hostPath field so PersistentVolume create directory on the node.
2. Add PersistenceVolumeClaim objects to your manifest and reference them from User deployment object.
3. Test PersistentVolume: create any file inside the container in the volume directory, scale down deployment or delete pod, let replicaset automatically create pod, ensure that file still exists.

**Scrips used to build, push to docker hub (https://hub.docker.com/) and run the applications**

The task uses all the code from Task 1 without EXPOSE 8082 (8081) line in Dockerfiles.

- docker system prune -a
- docker volume prune
- cd cd ./task1/post-service
- mvn clean install
- docker build -t eds832/postapplication:2.0.0 ./
- login to docker: docker login -u your_login -p your_password
- docker push eds832/postapplication:2.0.0
- cd ./../user-service
- mvn clean install
- docker build -t eds832/userapplication:2.0.0 ./
- docker push eds832/userapplication:2.0.0
- cd ../..
- kubectl apply -f ./
- kubectl apply -f ./databases
- kubectl apply -f ./user
- kubectl apply -f ./post
- kubectl get all -n=k8s-program
- kubectl delete all --all -n=k8s-program

**Applications run on:**

- http://localhost:30083/swagger-ui/index.html
- http://localhost:30084/swagger-ui/index.html