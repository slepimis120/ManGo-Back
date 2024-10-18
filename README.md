# ManGo \[Backend\]
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)

ManGo is an application similar to Uber for ride ordering. This repository contains the backend part of the application. The frontend part of the application is located in a separate repository linked [here](https://github.com/slepimis120/ManGo-Front).

## Main Functionalities

- **User Authentication**: Users can register, log in, and manage their profiles.
- **Ride Ordering**: Users can order rides, view available vehicles, and track their ride status.
- **Driver Management**: Drivers can update their location, view assigned rides, and manage their availability.
- **Admin Panel**: Admins can manage users, drivers, and vehicles.

## Main Endpoints

- **User Endpoints**:
  - `POST /user/register`: Register a new user.
  - `POST /user/login`: Log in a user.
  - `GET /user/profile`: Get the profile of the logged-in user.

- **Vehicle Endpoints**:
  - `GET /vehicle`: Get a list of all vehicles.
  - `PUT /vehicle/{id}/location`: Update the location of a vehicle (Driver only).

- **Ride Endpoints**:
  - `POST /ride/order`: Order a new ride.
  - `GET /ride/status/{id}`: Get the status of a ride.

## How to Run the Project

### Prerequisites

- Java 17
- Maven
- A running instance of the frontend application (refer to the frontend repository for setup instructions)

### Steps to Run

1. **Clone the Repository**:
   ```sh
   git clone https://github.com/yourusername/ManGo-Back.git
   cd ManGo-Back
   ```

2. **Set Up Environment Variables**:
   Ensure that the JAVA_HOME environment variable is set to your JDK installation directory.  

4. **Build the Project**:  
  ```sh
  mvn clean install
  Run the Application:  
  mvn spring-boot:run
```

4. **Access the Application**:
  The backend server will be running at http://localhost:8080. You can interact with the API using tools like Postman or through the frontend application.
