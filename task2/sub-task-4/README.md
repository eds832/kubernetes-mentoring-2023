## Sub-task 4: Stateful Sets

1. Use StatefulSet object (not Deployment) to create databases. 
2. Configure default storage class "hostpath" for volume claim templates, so allowing k8s to provision storage with default provisioner (PersistentVolume will be created automatically).
3. Create 2 Services (one for each StatefulSet of your system). Use ClusterIP service type to restrict external access.

_Note_: You can also use `kubectl port-forward pod-name 5433:5432` (local machine port:container port) console command to temporarily open access to the database pod <br>

**Scrips used to build, push to docker hub (https://hub.docker.com/) and run the applications**

- kubectl apply -f ./
- kubectl apply -f ./databases
- kubectl apply -f ./user
- kubectl apply -f ./post
- kubectl get all -n=k8s-program
- kubectl delete all --all -n=k8s-program

**Applications run on:**

- http://localhost:8083/swagger-ui/index.html
- http://localhost:8084/swagger-ui/index.html