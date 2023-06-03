## Sub-task 3: Deployment strategies
In this module you will add a field to one of your services, and perform Rolling-update deployment.
1. To Post service add a new field `topic (:String)`. This is the topic for the post. You can specify it when creating a new post and when updating existing post. This field also should be returned at the responses for POST, PUT and GET operations.
2. Build a new docker image of application with changes and push it to the Docker Hub (specify another version of container).
3. Add Rolling-update deployment strategy to your deployments at manifest files and apply the  manifest, so the old versions of microservices are deployed and running.
4. Set app version of app containers to the new one and apply manifest one more time. Make sure that new changes are deployed.

## Sub-task 4: Deployment history
As you deployed a new version of your application, you can see the history of your deployments. Your task is to roll back to previous version of your deployment without changing your manifest files.
Put in comments the solution of this task.

**Scrips used to update and run the applications**

For Rolling-update:

- cd ./task1/post-service
- mvn clean install
- docker build -t eds832/postapplication:5.0.0 ./
- login to docker: docker login -u your_login -p your_password
- docker push eds832/postapplication:5.0.0
- cd ../..
- kubectl apply -f ./config
- kubectl apply -f ./databases
- kubectl apply -f ./post

For roll-back:

- kubectl rollout history statefulset/post-statefulset-database -n=k8s-program
- kubectl rollout undo statefulset/post-statefulset-database -n=k8s-program --to-revision=1
- kubectl rollout history deployment/post-app -n=k8s-program
- kubectl rollout undo deployment/post-app -n=k8s-program --to-revision=1

At the end:

- kubectl get all -n=k8s-program
- kubectl delete all --all -n=k8s-program

**Applications run on:**

- http://localhost:30083/swagger-ui/index.html
- http://localhost:30084/swagger-ui/index.html
- http://localhost:30083/actuator/health/
- http://localhost:30084/actuator/health/