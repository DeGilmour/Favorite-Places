FROM maven AS build

WORKDIR /app
COPY pom.xml . 
RUN mvn dependency:resolve

COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk


WORKDIR /app


COPY --from=build /app/target/*.jar /app/app.jar


EXPOSE 8080


ENV SPRING_CONFIG_LOCATION=classpath:/application.properties,optional:classpath:/application.yml,optional:file:/app/config/.env

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
