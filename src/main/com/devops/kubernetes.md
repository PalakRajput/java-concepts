## Kubernetes

Open source container orchestration framework originally developed by google.
Orchestration tools offer:

1. High availability - no downtime
2. Scalability - high performance(high response rate to users/app loads faster)
3. Disaster recovery - backup and restore

### k8s Components

1. **Node(Worker node)**: A server(VM or physical)
2. **Pod**: Smallest unit of k8s. Abstraction over container. It creates running env/layer on top of containers. It is
   usually meant to run 1 container in 1 pod though we can run multiple containers in pod also. Each pod gets their own
   IP address which can be used for communication but pods are ephemeral so whenever a new pod is created or pod
   restarts a new ip address is assigned to it.
3. **Service**: It is a permanent ip address which can be attached to a pod and the lifecycle of service and pods are
   not connected so even if pod dies we don't have to replace the ip address. Can be internal(for db which we don't want
   to expose to outside world) or external service(can be accessible via browser).
4. **Ingress**: In service the url is not friendly so for friendly url ingress is used. The request goes to ingress, and
   then it is forwarded to appropriate service.
5. **Configmap**: Contains configuration data like urls to other service, env variables which is external to the
   application. Putting config data in configmap will not result in rebuilding the image every time in case of value
   change.
6. **Secret**: Used for storing credentials.
7. **Volumes**: To store container data. It's like an external hard drive plugged in to k8s cluster because k8s doesn't
   manage any persistence.
8. **Deployment**: Blueprint for pods. It is another abstraction on top of pods.
