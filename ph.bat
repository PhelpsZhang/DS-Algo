@echo off
call mvn clean compile
call mvn package
call java -jar target/Algo-1.0-SNAPSHOT.jar
