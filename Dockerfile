FROM openjdk:11

COPY target/FamilyApp-0.0.1-SNAPSHOT.jar FamilyApp-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/FamilyApp-0.0.1-SNAPSHOT.jar"]