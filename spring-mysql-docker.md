## STEP 0

Instal Java

`$ sudo apt-get install openjdk-8-jdk`

## STEP 1

Install Docker on you machine and create a network

`$ docker network create employee-mysql`

## STEP 2

Use MySQL Image published by Docker Hub (https://hub.docker.com/_/mysql/) Command to run the mysql container

`$ docker container run --name mysqldb --network employee-mysql -e MYSQL_ROOT_PASSWORD=Welcome#123 -e MYSQL_DATABASE=playground -e MYSQL_USER=shagunbandi -e MYSQL_PASSWORD=Welcome#123 -d mysql:8`

Now we have a container running, with the name of mysqldb, root and user password as Welcome#123 and a new DB named playground

## STEP 3

Application.properties should look like this

```
spring.datasource.url=jdbc:mysql://mysqldb:3306/playground
spring.datasource.username=shagunbandi
spring.datasource.password=Welcome#123
spring.datasource.platform=mysql
spring.datasource.initialization-mode=always

# Keep the connection alive if  idle for a long time (needed in production)
spring.datasource.testWhileIdle = true
spring.datasource.validationQuery = SELECT 1

# Show or not log for each sql query
spring.jpa.show-sql = true

# Hibernate ddl auto (create, create-drop, update)
spring.jpa.hibernate.ddl-auto = update

# Naming strategy
spring.jpa.hibernate.naming-strategy = org.hibernate.cfg.ImprovedNamingStrategy

# Use spring.jpa.properties.* for Hibernate native properties (the prefix is
# stripped before adding them to the entity manager)

# The SQL dialect makes Hibernate generate better SQL for the chosen database
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect
```

Give it a final name if you want

```
	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<finalName>blog-application</finalName>
				</configuration>
			</plugin>
		</plugins>
	</build>
```

## STEP 4

Now lets build a dockerfile

```
FROM openjdk:8
ADD target/blog-application.jar blog-application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "blog-application.jar"]
```

So get the base image for openjdk:8, copy the packaged jar to docker, then expose 8080 port from container. Entrypoint will run start the execution of jar

## STEP 5

package the project

`./mvnw package`

dockerise it

`docker build . -t blog-application`

## STEP 6

run the image

just for ref - docker run -p 8080:8080 --name blog-application --link mysql-standalone:mysql -d blog-application -

`$ docker container run --network employee-mysql --name blog-application -p 8080:8080 -d blog-application`

## STEP 7

Setup Apache

<VirtualHost \*:80>
ServerName 130.211.203.237
ServerAlias 130.211.203.237
ServerAdmin webmaster@130.211.203.237
DocumentRoot /var/www/html/build

    ErrorLog ${APACHE_LOG_DIR}/example.com-error.log
    CustomLog ${APACHE_LOG_DIR}/example.com-access.log combined

    ProxyPreserveHost On
    ProxyPass /api/ http://130.211.203.237:8080/
    ProxyPassReverse /api// http://130.211.203.237:8080/
    ProxyPass / http://130.211.203.237:8800/

</VirtualHost>
