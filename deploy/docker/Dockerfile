FROM gcr.io/distroless/java17-debian11

MAINTAINER Edson Rosas "mantillagka@gmail.com"

ENV TZ ${TZ}

# Default to UTF-8 file.encoding
ENV LANG C.UTF-8

EXPOSE 8080

WORKDIR /api

COPY neoris-customer-services/build/libs/*.jar /api/app.jar

CMD ["app.jar"]