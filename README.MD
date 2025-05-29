# API Performance Testing with Gatling (Java)
## Why Choose Gatling over Other Performance Testing Tools?
When selecting a performance testing tool, it's important to evaluate the strengths and limitations of each. Below is a comparison of Gatling with other popular tools like JMeter and LoadRunner, focusing on CI/CD integration, maintainability, cost, and results management.

🔹 Apache JMeter
Pros:
Intuitive UI: Easy to use, even without referencing documentation.
Good for quick manual test setups and exploratory performance testing.
Cons:
Limited CI/CD Integration: Cannot be easily run as part of a build pipeline for faster feedback.
Script Maintenance: Scripts require careful version control and are harder to manage in a collaborative environment.
Results Management: Lacks built-in support for historical result archiving or dashboarding.

🔹 LoadRunner
Pros:
Enterprise-grade tool with robust test execution and reporting.
Historical results available through Performance Center.
Cons:
Cost: Licensed tool with significant cost implications.
Execution Dependency: Requires Performance Center Scheduler to run tests.
Complexity: Requires coding skills to create and maintain scripts.

🔹 Gatling
Pros:
CI/CD Friendly: Seamlessly integrates with Jenkins and other pipelines.
Script Management: Gatling scripts are written in code (Scala/Java/Kotlin) and can be version-controlled in Git (e.g., Bitbucket).
Result Reporting:
Test results can be uploaded to S3 Buckets.
HTML reports are automatically generated and accessible from Jenkins builds.
Lightweight and fast execution.
Cons:
Requires some coding knowledge (supports multiple languages like Java, Scala, kotlin etc), but offers greater flexibility and reusability in return.


This project demonstrates API performance testing using [Gatling](https://gatling.io/) with Java. It includes sample simulations for REST API endpoints.
## Project Structure

- `src/test/java/common/ProjectInfo.java`: Configuration constants (base URL, user count, etc.)
- `src/test/java/simulations/LoadSimulation.java`: Main Gatling simulation scenario.

## Prerequisites

- Java 21 or higher
- Maven 3.x

## How to Run

1. Clone the repository.
2. Navigate to the project directory.
3. Run the Gatling simulation using Maven cmd 
    ```
    mvn gatling:test
    ```
4. After execution, reports will be available in `target/gatling` directory.

## Configuration

You can update test parameters in `common/ProjectInfo.java`:

- `BASE_URL`: API base URL
- `USER_COUNT`: Number of virtual users
- `RAMP_DURATION`: Ramp-up duration (seconds)
- `TEST_DURATION`: Total test duration (seconds)

## Dependencies

- Gatling Java API
- JUnit (for test scope)


## To add new API for Performance test

To add a new API endpoint for performance testing:

1. **Create a Simulation Class**  
   - In `src/test/java/simulations/`, create a new Java class (e.g., `NewApiSimulation.java`).
   - Extend `Simulation` and define your scenario using Gatling's Java DSL.

2. **Configure the Scenario**  
   - Set up HTTP requests, checks, and user injection profiles in your new simulation class.
   - Use configuration constants from `common/ProjectInfo.java` as needed.

3. **Register the Simulation**  
   - By default, Gatling will discover all simulation classes in the test sources.
   - To run a specific simulation, use:  
     ```
     mvn gatling:test -Dgatling.simulationClass=simulations.NewApiSimulation
     ```

## Main Execution Flow at Runtime

1. **Initialization**  
   - Gatling loads configuration from `ProjectInfo.java` and simulation classes.

2. **Simulation Execution**  
   - The selected simulation defines:
     - HTTP protocol configuration (base URL, headers)
     - Scenario steps (requests, pauses, checks)
     - User injection profile (number of users, ramp-up, duration)

3. **Test Run**  
   - Gatling injects virtual users and executes the scenario as defined.
   - Results (response times, success/failure, etc.) are collected.

4. **Reporting**  
   - After execution, HTML reports are generated in `target/gatling`.
   - Sample report uploaded to target/gatling/loadsimulation-20250529083649185/index.html
