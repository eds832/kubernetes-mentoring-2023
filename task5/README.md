# Table of Content

- [What to do](#what-to-do)
- [Sub-task 1: Ingress](#sub-task-1--ingress)

## What to do
In this module you will learn how to install ingress controller and route traffic to your applications. Also, you will practice helm.

## Sub-task 1: Ingress
1. Install ingress controller using helm chart. ([guide](https://kube-workshop.benco.io/08-helm-ingress/))
2. Change Services type to ClusterIP to restrict external access.  
3. Create ingress resource and route your traffic using rules. 
4. Configure rewrite-target of path using annotations. Example routing: from `http://localhost/posts/api/v1/greeting` to `http://posts:8080/api/v1/greeting`. ([ref docs](https://kubernetes.github.io/ingress-nginx/examples/rewrite/#rewrite-target))

**Scripts used in the task**

- docker system prune -a
- docker volume prune
- helm lint ./k8/
- helm install --dry-run release-1 ./k8/
- helm install release-1 ./k8/
- helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
- helm repo update
- helm install nginx-ingress ingress-nginx/ingress-nginx --set controller.publishService.enabled=true --namespace k8s-program
- kubectl get all -n=k8s-program
- helm uninstall release-1
- kubectl delete namespace k8s-program