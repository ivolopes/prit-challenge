Para rodar a api é necessário rodar os scripts abaixo na raiz do projeto:

1 - Gerar o arquivo JAR

./mvnw clean install

2 - Rodar o projeto

2.1 - Localmente utilizando JAVA 11 

java -jar target/api-0.0.1-SNAPSHOT.jar

2.2 - Docker

- docker build -t prit-api .
- docker run -p 8080:8080 --rm prit-api
