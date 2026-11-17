# Kubernetes Demo

A Kubernetes setup running a Spring application, with Traefik for ingress and load balancing and Argo CD for automated deployments.

## Architecture

- The application runs in two Kubernetes pods.
- Each pod contains a Spring application container.
- Traefik(Ingressor) routes incoming traffic to the application pods.
- Loadbalncer works on cloudprovider like AWS with default traefik install

## Traefik Setup

Traefik is installed using Helm. Its configuration depends on the environment:

### AWS

Traefik uses the default Helm chart configuration with a load balancer in front of the application pods.

### Local kind Cluster

The local kind cluster has no load balancer. Traefik is exposed through a `NodePort` service by overriding the default Helm chart configuration.

## Automated Deployments with Argo CD

Argo CD is installed to monitor the Kubernetes manifests and apply changes automatically.

- `argocd/application.yaml` defines the Argo CD application.
- Argo CD monitors infrastructure manifests such as `app/deployment.yml`.
- When an image tag changes in the monitored manifests, Argo CD updates the deployment so the pods use the new image.

* Project "spring_for_k8" has the workflow to update infrastructure manifest ie image version in `app/deployment.yml`

## Command Reference

`zmisc/Commands ran.java` lists most of the commands used to:

- Install Helm, Traefik, and Argo CD.
- Apply Kubernetes manifests.
