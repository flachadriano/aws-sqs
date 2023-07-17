# aws-sqs
Example running a SQS workflow

## Running kubernetes

Install Kind: https://kind.sigs.k8s.io/docs/user/quick-start/#installing-from-release-binaries


Install Kubectl: https://kubernetes.io/docs/tasks/tools/install-kubectl-linux/#install-kubectl-on-linux


After install the Kind, run the below instructions:
``` sh
kind create cluster --config config.yml
```

## Creating a deployment

Open the terminal, inside the root folder of the project.

In the below example, it is creating a deployment of consummer project:
``` sh
kubectl create deployment --image public.ecr.aws/u3c2v2u1/patient-consummer:latest --dry-run -o yaml consummer > consummer-deployment.yml
```

If you just want to run a pre-compiled `yml` file, run as below:
```sh
kubectl apply -f consummer-deployment.yml
```

## Delete a deployment

Execute the command in the terminal, consummer is the deployment name:
```sh
kubectl delete deployment consummer
```

## Exposing a service

Open the terminal, inside the root folder of the project.

In the below example, it is creating a port to a service:
``` sh
kubectl expose deployment consummer --port 8080 --target-port 8080
```

It is possible to export the created service as below:
```sh
kubectl get service consummer -o yaml > consummer-service.yml
```

If you want to know which is the pods labels, run:
```sh
kubectl get pod --show-labels
```

To know all the available endpoints of a service, run:
```sh
kubectl get endpoints consummer
```

You can call this server, running a curl to something like this:
```sh
curl http://consummer.default.svc.cluster.local
```

If you just want to run a pre-compiled `yml` file, run as below:
```sh
kubectl apply -f consummer-service.yml
```

## Delete a service

Execute the command in the terminal, consummer is the service name:
```sh
kubectl delete service consummer
```

## Create a config map

Access the root folder of the project, and run:
```sh
kubectl create configmap aws-config-env --from-file=aws-config.env
```

How to see the configmaps:
```sh
kubectl get configmap
```

## How to get pod log

List the pods:
```sh
kubectl get pod
```

Get logs:
```sh
kubectl logs $POD_NAME
```

## How to execute bash

List the pods:
```sh
kubectl get pod
```

Execute the bash:
```sh
kubectl exec -it $POD_NAME -- /bin/ash
```

## Setup env

```sh
kubectl delete deployment consummer
kubectl delete service consummer
kubectl delete configmap aws-config-env

kubectl create configmap aws-config-env --from-file=aws-config.env
kubectl apply -f consummer-volume-claim.yml
kubectl apply -f consummer-deployment.yml
kubectl apply -f consummer-service.yml
```
