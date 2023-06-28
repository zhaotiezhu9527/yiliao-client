FROM openjdk
EXPOSE 8522
VOLUME /tmp
#ADD task.jar /app.jar
ADD task/target/task.jar /app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Duser.timezone=GMT+8","-jar","/app.jar"]
