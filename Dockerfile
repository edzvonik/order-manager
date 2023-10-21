FROM openjdk:17

WORKDIR /app

ENV DB_NAME=jdbc:postgresql://database:5432/order_manager
ENV DB_USER=postgres
ENV DB_PASSWORD=postgres

COPY target/order-manager-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java", "-jar", "order-manager-0.0.1-SNAPSHOT.jar"]