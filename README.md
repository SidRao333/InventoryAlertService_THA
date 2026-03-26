Build and Run Instructions

Prerequisites
JDK 17 or higher
Maven (or use given ./mvnw wrapper)

Build the Project:
./mvnw clean install

Run the Application:
./mvnw spring-boot:run

Run Tests:

-Run all tests
./mvnw test

-Run inventory tests
./mvnw test -Dtest=InventoryServiceTest

-Run Alert tests
./mvnw test -Dtest=AlertTest

-Run a specific test
./mvnw test -Dtest=InventoryServiceTest#testUpdateStock_Success

Note:
Could not implement RestAPI and could not containerize the project using docker.
Used spring initialzr and lombok to avoid boilerplate code.
Persistence is currently handled in-memory using ConcurrentHashMap.
Robust validation required.
