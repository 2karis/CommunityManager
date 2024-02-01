FROM  maven:3.9.6-eclipse-temurin-21-alpine

WORKDIR /app

COPY pom.xml /app/pom.xml

RUN mvn dependency:go-offline

COPY src /app/src

RUN mvn install -Dskiptests

CMD ["mvn", "spring-boot:run"]

