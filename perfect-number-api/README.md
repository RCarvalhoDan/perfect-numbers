#Perfect Number Spring Boot Application

This project provides two endpoints:
1. An endpoint to check if a given number is a perfect number.
2. An endpoint to return all perfect numbers in a given range.

### Prerequisites
- Java 21 or higher
- Maven

### Project Structure
```
src/main/java/com/perfectnumber/api
│   PerfectNumberApplication.java
│
└───controller
│       PerfectNumberController.java
│
└───service
        PerfectNumberService.java
```

### Running the Application
1. Clone the repository:
```
   git clone <repository-url>
   cd perfect-number-springboot
```

2. Build the project
```
    mvn clean install
```

4. Run the application:
```
   mvn spring-boot:run
```

The application will start on http://localhost:8080.

### Endpoint
1. Check if a number is perfect:
- URL: /perfect-number/{number}
- Method: GET
- Response:
   - true if the number is perfect
   - false if the number is not perfect
Example:
```
    curl -X GET "http://localhost:8080/perfect-number/28"
```

2. Get perfect numbers in a range:
- URL: /perfect-number/in-range
- Method: GET
- Parameters:
  - lowerBoundValue (long, required): the start of the range
  - upperBoundValue (long, required): the end of the range
- Response: a list of perfect numbers in the specified range
Example:
```
    curl -X GET "http://localhost:8080/perfect-number/in-range?lowerBoundValue=1&upperBoundValue=10000"
```
### License
This project is licensed under the MIT License.