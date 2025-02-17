FROM eclipse-temurin:21-jdk  AS buildstage 

RUN apt-get update && apt-get install -y maven

WORKDIR /app

COPY pom.xml .
COPY src /app/src
COPY Wallet_QHKV1HXJX2PK6K3Q /app/wallet

ENV TNS_ADMIN=/app/wallet

RUN mvn clean package -DskipTests=true

FROM eclipse-temurin:21-jdk  

COPY --from=buildstage /app/target/signos_vitales_alertas-0.0.1-SNAPSHOT.jar /app/signos_vitales_alertas.jar

COPY Wallet_QHKV1HXJX2PK6K3Q /app/wallet

ENV TNS_ADMIN=/app/wallet

EXPOSE 8083

ENTRYPOINT [ "java", "-jar","/app/signos_vitales_alertas.jar" ]
