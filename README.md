## Grind of JAVA, DSA, LLD

[//]: # (CI badge)
[![CI](https://github.com/gaganjawa/grind/actions/workflows/maven.yml/badge.svg)](https://github.com/gaganjawa/grind/actions/workflows/maven.yml)
[![Coverage](https://img.shields.io/codecov/c/gh/gaganjawa/grind?logo=codecov&label=coverage)](https://codecov.io/gh/gaganjawa/grind)

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

The workflow now generates a JaCoCo coverage report and uploads it to Codecov. The coverage badge above reflects the most recent Codecov report for the repository.

To enable Codecov uploads for private repos or to increase security, set the `CODECOV_TOKEN` secret in your repository settings and add `token: ${{ secrets.CODECOV_TOKEN }}` under the Codecov action 'with' block in `.github/workflows/maven.yml`.
