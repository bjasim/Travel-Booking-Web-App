# Travel-Booking-Web-App

# Hotel Booking System

A modern, robust hotel booking system built with Spring Boot and Angular, featuring a RESTful API backend and a responsive frontend interface.

## Features

- **RESTful API Architecture**: Modern backend implementation using Spring Boot
- **Database Integration**: Seamless integration with MySQL database
- **Object-Oriented Design**: Clean architecture with proper separation of concerns
- **Design Patterns**: Implementation of industry-standard design patterns
- **Cross-Origin Support**: Built-in CORS support for secure cross-origin requests
- **Data Validation**: Comprehensive input validation for all user interactions
- **Order Management**: Complete order processing system with tracking numbers
- **Customer Management**: Built-in customer database with sample data

## Technical Stack

### Backend
- Spring Boot
- Spring Data JPA
- MySQL Database
- Lombok
- REST Repositories

### Frontend
- Angular
- JavaScript
- Modern UI/UX design

## Architecture

The application follows a clean, layered architecture:

- **Controllers**: Handle HTTP requests and responses
- **Services**: Implement business logic
- **DAO**: Data Access Objects for database operations
- **Entities**: Domain models and data structures
- **Config**: Application configuration and settings

## Key Components

- **Checkout System**: Complete order processing with cart management
- **Vacation Packages**: Comprehensive vacation package management
- **Customer Management**: Customer data handling and storage
- **Order Tracking**: Unique order tracking numbers for each transaction
- **Data Validation**: Robust input validation for all user interactions

## Database Structure

The system maintains several key data tables:
- Customer information
- Vacation packages
- Order details
- Cart items
- Transaction records

## API Endpoints

The system provides RESTful endpoints for:
- Order processing
- Customer management
- Vacation package queries
- Cart operations

## Security Features

- Cross-Origin Resource Sharing (CORS) support
- Input validation
- Secure data transmission
- Protected API endpoints

## Getting Started

### Prerequisites
- Java 17 or higher
- Node.js and npm
- Angular CLI
- MySQL Server
- Maven

### Backend Setup (Spring Boot)
1. Clone the repository
2. Navigate to the backend directory
3. Configure the database connection in `application.properties`
4. Run the application using one of these methods:
   - Using IntelliJ IDEA:
     - Open the project in IntelliJ
     - Run the main application class
   - Using Maven command line:
     ```bash
     mvn spring-boot:run
     ```
   - Using the JAR file:
     ```bash
     mvn clean package
     java -jar target/your-application-name.jar
     ```
   The backend will start on `http://localhost:8080`

### Frontend Setup (Angular)
1. Navigate to the frontend directory
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the development server:
   ```bash
   ng serve
   ```
   The frontend will be available at `http://localhost:4200`

### Accessing the Application
- Frontend: Open `http://localhost:4200` in your browser
- Backend API: Available at `http://localhost:8080/api`

### Database Setup
1. Ensure MySQL server is running
2. The application will automatically create the necessary tables on first run
3. Sample customer data will be automatically populated

### Development
- Backend hot-reload is enabled by default when running through IntelliJ or `spring-boot:run`
- Frontend hot-reload is enabled by default with `ng serve`
