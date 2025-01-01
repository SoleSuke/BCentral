TASK PROJECT APPLICATION FOR BCENTRAL

Please proceed in order, start the Kafka docker container first(I) and then start the Program(II):
    * Check that you have Java21
    * Check that your port 9092 is free or change the port in ./src/main/resources/application.properties


 I.- For start Kafka's docker container you must:
I.1.- Download docker for your distribution.
I.2.- Going to subdirectory kafka. Example: cd ./kafka/
I.3.- Start the container with the docker compose existent in kafka directory. Example: docker-compose up -d

 II- These are the instructions for execute the program.
II.1.- Download the code
II.2.- Make sure that you have maven version 3 at least.
II.3.- Execute: mvn compile
II.4.- If you want to execute the Junit test, please execute: mvn test;
II.5.1.- If you want to execute the Program and you are using an IDE like Eclipse or IntelliJ IDEA, please open the program; compile the program and run with the IDE.
II.5.2.- If you want to execute the Program by command line please download the fat jar taskProject-0.0.1-SNAPSHOT.jar.
       - Download the code of this Project to a DestinyProjectDirectory and the move to DestinyProjectDirectory ==> Execute: cd <DestinityProjectDirectoy>
       - Execute: mvn clean
       - Execute: mvn package
       - The fat jar will be available in the <DestinityProjectDirectoy>/target/
       - Execute: java -jar taskProject-0.0.1-SNAPSHOT.jar
