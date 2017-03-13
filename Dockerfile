FROM payara/micro
MAINTAINER Ricardo Job <sousajob@gmail.com>
ADD target/pos-rest.war $PAYARA_PATH/
ENTRYPOINT ["java", "-jar", "payara-micro.jar", "--deploy", "pos-rest.war"]
#ENTRYPOINT ["java", "-jar", "aula-calculadora-jse-jar-with-dependencies.jar"]
