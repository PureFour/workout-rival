FROM openjdk:11
ADD build/libs/workout-rival-1.0.0.jar workoutRival.jar
EXPOSE 8080:8080
ENTRYPOINT ["java", "-jar", "workoutRival.jar"]