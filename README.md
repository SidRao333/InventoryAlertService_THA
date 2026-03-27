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

Rest API's: (Use postman or curl to test)

Add a product (Post) - 
curl -X POST http://localhost:8080/products -H "Content-Type: application/json" -d '{"id":"1","name":"Laptop","sku":"LAP123","category":"Electronics","currentStock":50,"reorderThreshold":10}'

Update stock (Patch) - 
curl -X PATCH http://localhost:8080/products/LAP123/stock -H "Content-Type: application/json" -d '{"delta":-41}'

Get in memory alert list (Get) -
curl http://localhost:8080/alerts


Note:
Could not implement RestAPI and could not containerize the project using docker.
Used spring initialzr and lombok to avoid boilerplate code.
Persistence is currently handled in-memory using ConcurrentHashMap.
Robust validation required.
