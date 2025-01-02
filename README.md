
# To-Do List Application

## Overview
The **To-Do List Application** is a simple desktop GUI-based application built using **Java Swing**. It allows users to create, update, and manage their tasks efficiently. The tasks are saved locally in a JSON file, ensuring they persist between sessions. Completed tasks are displayed with a strikethrough for easy identification.

## Features
- Add new tasks with a description.
- Mark tasks as completed (with a strikethrough effect).
- Delete tasks when no longer needed.
- Save tasks to a local JSON file.
- Reload saved tasks on application startup, including completed tasks.

## Technologies Used
- **Java**: Core programming language.
- **Swing**: For creating the graphical user interface.
- **Gson**: For JSON serialization and deserialization.
- **Java Util Logging**: For logging errors and events.

## Setup Instructions

### Prerequisites
1. **Java Development Kit (JDK)**:
   Ensure that **JDK 16 or later** is installed on your machine.
2. **IDE**:
   You can use any IDE, such as **IntelliJ IDEA**, **Eclipse**, or **NetBeans**.

### Steps
1. Clone or download the project files.
2. Open the project in your preferred IDE.
3. Add the **Gson Library** to the project:
   - If you're using Maven, include the following dependency in your `pom.xml`:
     ```xml
     <dependency>
         <groupId>com.google.code.gson</groupId>
         <artifactId>gson</artifactId>
         <version>2.10.1</version>
     </dependency>
     ```
   - If not using Maven, download the Gson JAR file from [Gson GitHub Releases](https://github.com/google/gson) and add it to your project classpath.
4. Run the `App.java` file to start the application.

## Usage
1. **Adding Tasks**:
   - Click the **"Add Task"** button.
   - Enter the task description in the text field.

2. **Marking Tasks as Completed**:
   - Check the checkbox next to a task. This applies a strikethrough to the task text.

3. **Deleting Tasks**:
   - Click the **"X"** button to remove a task.

4. **Saving Tasks**:
   - Click the **"Save Tasks"** button to save your tasks to the `tasks.json` file.

5. **Reloading Tasks**:
   - Restart the application to reload the saved tasks, including completed ones.

## Project Structure
```
src/
├── App.java                 # Main entry point for the application
├── CommonConstants.java     # Stores reusable constants for GUI dimensions
├── TaskComponent.java       # Defines an individual task component
├── TodoListGUI.java         # Main GUI and logic for the application
└── tasks.json               # JSON file to store tasks (created automatically)
```

## Sample `tasks.json` File
Here’s an example of how tasks are stored in the JSON file:
```json
[
  {
    "text": "Complete homework",
    "completed": false
  },
  {
    "text": "Buy groceries",
    "completed": true
  }
]
```

## Known Issues
- **No Cloud Backup**: Tasks are only saved locally. Cloud backup is not implemented.
- **UI Limitations**: The GUI design is functional but basic. Advanced styling is not included.

## Future Enhancements
1. Add a feature to set deadlines for tasks.
2. Implement a search functionality for tasks.
3. Integrate a database (e.g., SQLite) for better data management.
4. Add drag-and-drop functionality to reorder tasks.
5. Create a mobile-friendly or web version of the application.

## Contributors
- **Suhas Kumar**

## License
This project is licensed under the **MIT License**. Feel free to use, modify, and distribute it.

