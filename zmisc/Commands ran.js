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

    kubectl port-forward svc/argocd-server -n argocd 8081:443
    https://localhost:8081
    initial pwd-kubectl -n argocd get secret argocd-initial-admin-secret -o jsonpath="{.data.password}" | base64 -d; echo
    e5SYwu47OoAWTQ6S
  }
