## Grind of JAVA, DSA, LLD

[//]: # (CI badge)
[![CI](https://github.com/gaganjawa/grind/actions/workflows/maven.yml/badge.svg)](https://github.com/gaganjawa/grind/actions/workflows/maven.yml)

## Running tests

This project includes JUnit 5 tests under `src/test/java` for the `org.lld.designpatterns` packages.

From PowerShell in the project root (requires Maven installed and on PATH):

```powershell
# Run all tests
mvn test

# Run a single test class (example)
mvn -Dtest=NotificationFactoryTest test
```

If you don't have Maven installed, open the project in IntelliJ IDEA or another IDE that supports Maven, refresh the Maven project, and run the tests from the IDE's test runner.

If tests refer to missing dependencies in your IDE, run `mvn test` once to download them and refresh the project.

## Continuous Integration (GitHub Actions)

A GitHub Actions workflow is included at `.github/workflows/maven.yml`. It runs the Maven test goal on every push and on pull requests to any branch.

To enable the CI badge above, the badge URL already points to your repository.
