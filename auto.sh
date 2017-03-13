mvn clean install
docker stop rest
docker rm rest
docker rmi victor/pos-rest
docker build -t victor/pos-rest .
docker run -p 8080:8080 -d --name rest victor/pos-rest

