# Favorite Places - Favorite Zip Codes

This project allows users to manage their favorite ZIP codes, view details about them, and add/remove ZIP codes from their list of favorites. The application uses an in-memory H2 database for development purposes, and the backend is built using Spring Boot.

## Features
- Add a ZIP code to your favorite list.
- View a list of favorite ZIP codes with details like street, neighborhood, and state.
- In-memory database for testing and development.

## Technologies Used
- **Spring Boot**: Framework for building the backend.
- **H2 Database**: In-memory database to store ZIP code details.
- **Docker**: Containerization for easy deployment and running of the application.

## Prerequisites
Make sure you have the following installed:
- **Java 17+** (for local development and Docker image build)
- **Maven** (for building the application locally)
- **Docker** (to containerize the application)

## Setting Up the Project

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/favorite-places.git
cd favorite-places
```
### 2. Build the Application Locally
```bash
mvn clean install
```

### 3. Run the Application Locally (Optional)

If you want to run the application locally without Docker, use:

``` bash 
mvn spring-boot:run
```
### Run the Docker Container

After building the image, run the following command to start the container:
``` bash 
docker build -t favorite-places-app .
```

## API Endpoints
Add a ZIP Code to Your Favorites

    Endpoint: POST /api/cep/favorite
    Parameters: zipCode (string) - The ZIP code to add.
    Example Request:

    POST /api/cep/favorite?zipCode=12345

Get All Favorite ZIP Codes

    Endpoint: GET /api/cep/favorites
    Response: Returns a list of favorite ZIP codes.

View Details of a Specific ZIP Code

    Endpoint: GET /api/zipcode/{zipCode}
    Response: Returns details of a specific ZIP code.

Generate a Random ZIP Code

    Endpoint: GET /api/zipcode/generateRandom
    Response: Returns a randomly generated ZIP code.

Docker Configuration

The application is configured to use an in-memory H2 database for development and testing. The Docker container will use this configuration by default, add them in .env file in your main folder.

Docker Environment Variables

    SPRING_DATASOURCE_URL: Configures the JDBC URL for the H2 database.
        Default: jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
    SPRING_DATASOURCE_USERNAME: Configures the database username.
        Default: sa
    SPRING_DATASOURCE_PASSWORD: Configures the database password.
        Default: password
    SPRING_JPA_HIBERNATE_DDL_AUTO: Automatically updates the database schema.
        Default: update