Install MySQL with username: root and password: root

Generate agent:  Run mvn assembly:assembly

Create database: Run mvn jetty:run-war with POM property set to <dbunit.skip>true</dbunit.skip>

Load data into database: Run mvn test with POM property set to <dbunit.skip>false</dbunit.skip>

Run application: Run mvn jetty:run-war with POM property set to <dbunit.skip>true</dbunit.skip>

Run agent: java -jar Optiploy-0.0.1-SNAPSHOT-jar-with-dependencies.jar

Log in: http://localhost:8080/login.html

Username: admin | Password: admin

Still have to work on the job page but to do a sample build, go to Job Actions/Job List/Pick job and click small build icon.  Going to flesh this out over the next week.  My kids permitting...



* I don't know if anyone is even reading this yet since this application isn't fully functional but this application's foundation is based upon AppFuse.  Many thanks to Matt Raible for awesome code.

