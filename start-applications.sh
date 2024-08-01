#!/bin/bash

# Navigate to the backend directory and start the Spring Boot application
echo "Starting Spring Boot backend..."
cd perfect-number-api
./mvnw spring-boot:run &
BACKEND_PID=$!

# Navigate to the frontend directory and start the React application
echo "Starting React frontend..."
cd ../perfect-number-app
npm install
npm start &
FRONTEND_PID=$!

# Wait for both processes to finish
wait $BACKEND_PID $FRONTEND_PID