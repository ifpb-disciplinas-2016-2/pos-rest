mvn clean install
docker stop rest
docker rm rest
docker rmi natarajan/pos-rest
docker build -t natarajan/pos-rest .
docker run -p 8080:8080 -d --name rest natarajan/pos-rest

