# 1. compile java file
javac -cp "lib/mysql-connector-java-8.0.29.jar" -d out src\*.java


# 2. check the compiled classes and the MySQL connector:
java -cp "out;lib/mysql-connector-java-8.0.29.jar" Main


# 3. Package everything into a JAR:
jar cfm BetterHealth.jar manifest.txt -C out .


# 4. Run your JAR:
java -jar BetterHealth.jar