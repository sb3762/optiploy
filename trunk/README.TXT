Install MySQL with username: root and password: root

Generate agent:  Run mvn assembly:assembly

Create database: Run mvn jetty:run-war with POM property set to <dbunit.skip>true</dbunit.skip>

Load data into database: Run mvn test with POM property set to <dbunit.skip>false</dbunit.skip>

Run application: Run mvn jetty:run-war with POM property set to <dbunit.skip>true</dbunit.skip>

Run agent: java -jar Optiploy-0.0.1-SNAPSHOT-jar-with-dependencies.jar

Run job: http://localhost:8080/buildController.do?jobId=1&Test=Hi 

