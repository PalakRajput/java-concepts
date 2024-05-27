## Maven

Maven is build tool like Ant and Gradle.

In the projects which uses maven build tool, we can add maven wrapper files(mvnw, mvnw.cmd) and then anyone can build
the project without installing mvn on their machine.
To add maven wrapper run the command in the project directory: mvn wrapper:wrapper

### pom.xml
#### Tags in pom.xml:
groupId and artifactId uniquely identifies the project.
version -> version of the project(SNAPSHOT means working project)

properties -> any properties to be used in pom.xml(generally versions)
dependencies -> group all the dependencies required for the project.
dependency -> specific dependency with groupId, artifactId, version, scope, etc...

### Maven lifecycle
mvn clean -> delete target folder
mvn compile -> compile src.main.java and put the compiled classes in target folder
mvn test -> also includes mvn compile and run the tests.
mvn package -> run compile, test and package to create jar file
mvn install -> creates jar and add the jar in local maven repository.

