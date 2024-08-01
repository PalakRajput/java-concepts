## Docker

Docker virtualizes application layer of OS whereas virtual machine virtualizes OS kernel + application layer.
OS Kernel is responsible for communicating with hardware and applications are based on OS Kernel.
Docker images can run much faster than VMs. And they are lightweight(few MBs) whereas VM size can be few GBs.
Docker images are immutable so if there is any change in dockerfile then we will have to create the image again.

### Image

The portable artifact with all the required dependencies, configs, etc.

### Container

Running image

#### Docker commands

1. docker ps -> to list all containers that are running
2. docker run <image-name:version(optional)> -> to pull( if image doesn't exist locally ) and run the image
3. docker pull <image-name:version(optional)> -> to pull the image
4. docker images -> list all the images on system
5. docker stop <container-id> -> to stop the container
6. docker start <container-id> -> to start the container
7. docker run -p <laptopPort:containerPort> <image-name:tag> -> docker run -p 5000:6000
8. docker ps -a -> all containers whether running or not.
9. docker run -p 3000:3000 -d image-name -> -d for running container in detach mode
10. docker run -p 3000:3000 -d -e VAR1=VAL1 VAR2=VAL2 VAR3=VAL3 -> -e to pass environment variables.
11. docker run -p 8080:8080 -d -e VAR1=VAL1 --net <docker-network> --name <container-name> image-name -> --net to
    specify docker network and --name to specify the container name.
12. docker rmi <image-id/image-name>

> Containers can run on same port provided that they are bound to different host machine port.

### Debugging Containers

1. docker logs <container-id/container-name>
2. docker exec -it <container-id/container-name> /bin/bash(/sh in case bash is not installed in the container) -> to
   open executable terminal of the container

### Docker Network

Docker creates its isolated docker network.
When 2 or more containers are deployed on same docker network, they can connect to each other just using container name
and the application outside of docker network can connect using host:port.

1. docker network create <docker-network-name> -> to create docker network
2. docker network ls -> to list docker networks

### Docker compose

Structured commands for running multiple containers who communicate with each other.
Docker compose creates a common network.

docker-compose.yaml:

```yaml
version: '3' #docker compose version
services: #list all containers
  <container-name>:
    image: <full-image-name-from-private-registry>
    ports:
      - 3001:3001 #host:container
  <container-name-1>:
    image: <image-name>
    ports:
      - 3000:3000 #host:container
    environment:
      - VAR1=VAL1
      - VAR2=VAL2
  <container-name-2>:
    image: <image-name>
    ports:
      - 8080:8080
    environment:
      - VAR1=VAL1
```

docker-compose -f docker-compose.yaml up -> to run the docker compose file

### Dockerfile

```text
FROM <base-image-name:tag>
ENV VAR1=VAL1 VAR2=VAL2
COPY source destination
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "jarName.jar"]
```

docker build -t image-name:version . -> to build docker image when the dockerfile is located in current directory(.)

### Log in to private registry and push images(ACR here)

docker login registryname.azurecr.io --username <username> --password <password>
docker push registry-path/image-name:tag

> If we want to push from local system then we need to tag the image with the registry repo and then push it otherwise
> it will be pushed to docker hub by default.
> so follow below commands:
>
> docker tag image:tag registry-repo-path/image:tag
> docker push image:tag
>
> docker tag oldName newName -> renaming the image.

We can use docker compose to and provide the application image name along with database.
For docker hub we don't need login credentials but if we are pulling image from private registry we need to log in to
that registry before pulling the image.

### Docker Volumes

Docker volumes are used for data persistent(for db or stateful apps).
Folder in physical host file system is mounted into the virtual file system of docker so the data gets automatically
replicated to the physical file system of host or to the virtual file system in case the container restarts.



