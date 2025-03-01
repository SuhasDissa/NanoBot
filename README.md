# Nano Bot Project

This project is designed to control a robot car with 4 wheels, servos, and speakers using a Raspberry Pi and the Pi4J library.

## Prerequisites

- **Hardware**: Raspberry Pi with connected motors, servos, and speakers
- **Software**:
    - IntelliJ IDEA
    - Maven
    - JDK 21 or higher

## Setup

1. **Clone the repository**:
    ```sh
    git clone <repository-url>
    cd <repository-directory>
    ```

2. **Open the project in IntelliJ IDEA**:
    - Open IntelliJ IDEA.
    - Select `File` -> `Open...`.
    - Navigate to the cloned repository directory and open it.

3. **Ensure Maven is installed**:
    - Maven is required to build the project. To check if Maven is installed, run the following command in the terminal:
        ```sh
        mvn -v
        ```

## Build the Project

To compile the project, run the following command in the terminal:
```sh
mvn clean package
```
This command will clean any previous builds and package the project into a JAR file.  

## License
This project is licensed under the GPL-v3 License. See the LICENSE file for details.