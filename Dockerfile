FROM openjdk:11
COPY target/productservice-0.0.1-SNAPSHOT.jar productservice-1.0.0.jar
ENTRYPOINT [ "java", "-jar", "/productservice-1.0.0.jar" ]
