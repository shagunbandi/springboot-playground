git pull
docker rmi $(docker images --filter "dangling=true" -q --no-trunc)
./mvnw package
docker build . -t blog-application
docker stop blog-application
docker rm blog-application
docker container run --network employee-mysql --name blog-application -p 8080:8080 -d blog-application
