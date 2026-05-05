# User Management System (Java MVC)

A simple, console-based User Management System implemented in Java using the Model-View-Controller (MVC) architectural pattern.

## Project Overview
This project provides a functional CLI for managing user records, including creating, reading, updating, and deleting (CRUD) users. It is designed with a clear separation of concerns across multiple layers: Model, View, Controller, Service, and Repository.

### Core Technologies
- **Language:** Plain Java (JDK 17+ recommended)
- **Persistence:** In-memory storage using `java.util.ArrayList`
- **Architecture:** MVC + Service/Repository layers

## Directory Structure
- `src/com/management/`
    - `Main.java`: Entry point and dependency injection.
    - `model/User.java`: The data entity (ID, Name, Email).
    - `repository/UserRepository.java`: Handles data access and in-memory storage.
    - `service/UserService.java`: Contains business logic and orchestrates repository calls.
    - `controller/UserController.java`: Bridge between the View and the Service.
    - `view/UserView.java`: Interactive console menu and user input handling.

## Building and Running

### Prerequisites
- Java Development Kit (JDK) installed.
- Terminal/Command Prompt access.

### Execution Commands
All commands should be run from the `src` directory:

1.  **Navigate to source folder:**
    ```powershell
    cd src
    ```

2.  **Compile the project:**
    ```powershell
    javac com/management/Main.java
    ```

3.  **Run the application:**
    ```powershell
    java com.management.Main
    ```

## Development Conventions

### Architecture
- **Model:** Simple POJO with getters/setters.
- **Repository:** Responsible for the collection of data. Returns boolean status for mutations (delete/update).
- **Service:** Handles business rules (e.g., entity creation).
- **Controller:** Strictly handles request/response flow.
- **View:** Uses `Scanner.nextLine()` to handle inputs with spaces and prevents buffer issues.

### Key Features
- **String-based IDs:** Allows flexible ID formats (e.g., `20230335-s`).
- **Selective Updates:** Users can update specific fields (ID, Name, Email) individually or all at once.
- **Validation:** Checks if a user exists before allowing updates or deletions.

## Future Improvements (TODO)
- [ ] **Data Persistence:** Implement file-based storage (CSV/JSON) to save users between sessions.
- [ ] **Input Validation:** Add Regex for email format and unique ID checks.
- [ ] **Advanced Search:** Implement name-based partial search.
