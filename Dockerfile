FROM openjdk:8-jdk-alpine
WORKDIR /app
ADD ./build/libs/wepinit-shop.war /app/wepinit-shop.war
ENTRYPOINT exec java $JAVA_OPTS -jar /app/wepinit-shop.war
