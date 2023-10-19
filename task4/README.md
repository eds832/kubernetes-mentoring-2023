# Table of Content

- [What to do](#what-to-do)
- [Sub-task 1: Helm charts](#sub-task-1--helm-chart-default-variables)
- [Sub-task 2: Helm chart helpers](#sub-task-2--helm-chart-helpers)

## What to do
In this module you will learn how to attach persistent storages to your applications. Also, you will understand how helm charts work.

## Sub-task 1: Helm chart default variables
1. Install helm [Official download link](https://helm.sh).
2. Add helm chart to deploy your applications. Make replica-count and namespace a helm values.
3. Add helm values file to store default values for helm variables.
4. Run helm using `helm install` command to deploy applications with default helm variables. Make sure, your applications are up and running.
5. Run helm once again, but this time set namespace and replica-count for `helm intall` to non-default values.

## Sub-task 2: Helm chart helpers
1. Create helm `_helpers.tpl` file and define next labels there: 
   - current date : use helm generator for it's value
   - version
2. Make config-map use values as labels from helm `_helpers.tpl` file.

**Scrips abd materials used to do the task4**

- docker system prune -a
- docker volume prune
- Downloaded and unpacked helm from: https://get.helm.sh/helm-v3.12.0-windows-amd64.zip
- helm lint ./k8/
- helm install --dry-run release-1 ./k8/
- helm install release-1 ./k8/
- helm uninstall release-1
- Templates: https://v2.helm.sh/docs/chart_template_guide/
- helm install release-14 ./k8/ --dry-run --debug --set userApp.spec.replicas=3 --set postApp.spec.replicas=3 --set namespace.name=k8-prog
- helm install release-14 ./k8/ --set userApp.spec.replicas=3 --set postApp.spec.replicas=3 --set namespace.name=k8-prog