FROM java:8
EXPOSE 8001
ADD blog-api-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=prod","--allowedOrigins=http://lbm.dsmynas.com:2000"]