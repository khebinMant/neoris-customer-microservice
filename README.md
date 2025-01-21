# neoris-customer-service

## Style Code

[ Code style configuration in the IDE ]

## Build

```bash
./gradlew clean build -x test
```

## Run

Run with gradle

```bash
./gradlew neoris-customer-services:bootRun
```

Run with jar

```bash
java -jar ./neoris-customer-services/build/libs/neoris-customer-services-1.0.0-SNAPSHOT.jar
```

## Build docker image

1) Build application

```shell
./gradlew clean build -x test
```

2) Build docker image

```shell
docker build --no-cache -t neoris-customer-services:1.0.0-SNAPSHOT -f deploy/docker/Dockerfile .
```

**OPTIONAL** - Test Docker image

```shell
docker run -it --rm --env-file ./deploy/docker/.env --name neoris-customer-services -p 8083:8080 neoris-customer-services:1.0.0-SNAPSHOT
```