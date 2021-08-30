FROM adoptopenjdk:11-jre-openj9

EXPOSE 8080

VOLUME /tmp

ARG DEPENDENCY=/target/dependency
COPY ${DEPENDENCY}/lib /app/lib
COPY ${DEPENDENCY}/resources /app
COPY /target/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","com.example.warehousemanagement.WarehouseManagementApplication"]