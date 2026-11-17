docker {
  docker build -t demo_app_img_name:v2 .
  shift img to kind env-   kind load docker-image demo_app_img_name:v2 --name spring-lab


  docker tag demo_app_img_name:v3 shivakumardwd/demo_app_img_name_on_hub:v2

  docker push shivakumardwd/demo_app_img_name_on_hub:v2
}

kubectl apply, label and logs {
  kubectl get pods --show-labels
  kubectl logs -l app=<result>

  or
  kubectl logs -f <pod-name>
    or
    kubectl logs -f deployment/spring-deployment-name



    kubectl apply -f deployment.yml
    kubectl get pods -o wide

    kubectl apply -f service.yaml
    kubectl get service -o wide
}

kind {
    kind delete cluster --name spring-lab
    kubctl get nodes

    kind create cluster --name spring-lab --config kind-config.yaml
    kubctl get nodes

    kind load docker-image demo_app_img_name:v1 --name spring-lab
    docker exec spring-lab-worker crictl images | grep spring-app
}

Helm-traefik {
    # Linux (official install script)
    curl -fsSL https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3 | bash
    helm version

    helm upgrade --install my-traefik traefik/traefik \
    --namespace traefik --create-namespace \
    -f traefik-values.yaml
}

Argo CD {
    kubectl create namespace argocd
    kubectl apply -n argocd --server-side -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml

    #once ready
    kubectl apply -f argocd/application.yaml

    kubectl port-forward svc/argocd-server -n argocd 8081:443
    https://localhost:8081
    initial pwd-kubectl -n argocd get secret argocd-initial-admin-secret -o jsonpath="{.data.password}" | base64 -d; echo
    e5SYwu47OoAWTQ6S

    # Scale down Deployments
    kubectl scale deployment --replicas=0 --all -n argocd
    # Scale down StatefulSets (like argocd-application-controller and argocd-redis if configured as such)
    kubectl scale statefulset --replicas=0 --all -n argocd
    #bring backup
    kubectl scale deployment --replicas=1 -n argocd --all
    kubectl scale statefulset --replicas=1 -n argocd --all
}

AWS {
    Loggin and cluster cration/context
    Created IAM user. Create Key/secrete key. Using which logged in from local terminal using "aws config" command;
    Used "eks" command to create cluster.
    Used Kubectl to interact with cluster.
    "kubectl config context/.." command to switch between AWS and local king cluster...

    Replicated kind work in AWS..
    Same apply
    But, Installed Traefik with default value -  no overriding; Gives LB.
    Issue of not installing ingress.yaml and class name {
        When traefik in Helm-installed with xyz release name, looks it created ingressClass with xyz.
        This same xyz should be mentioned in ingress.yml-rules file.
    }
}
