# 🧩 Maven Commands Cheat Sheet

A quick reference for commonly used Maven build, dependency, testing, and reporting commands.

---

## 🧹 **Build Lifecycle**

| Command             | Description                                                     |
| ------------------- | --------------------------------------------------------------- |
| `mvn clean`         | Cleans the target directory (removes compiled files & reports). |
| `mvn compile`       | Compiles source code (`src/main/java`).                         |
| `mvn test-compile`  | Compiles test source code (`src/test/java`).                    |
| `mvn test`          | Runs all unit tests.                                            |
| `mvn package`       | Builds the project and creates a JAR/WAR.                       |
| `mvn verify`        | Runs quality checks and integration tests.                      |
| `mvn install`       | Installs the package into the local Maven repo.                 |
| `mvn deploy`        | Deploys the package to a remote repo.                           |
| `mvn clean install` | Cleans, builds, tests, and installs the project.                |
| `mvn clean verify`  | Clean + build + verify quality.                                 |

---

## 🔁 **Dependency Management**

| Command                                 | Description                                                 |
| --------------------------------------- | ----------------------------------------------------------- |
| `mvn dependency:tree`                   | Shows dependency hierarchy (useful for conflict debugging). |
| `mvn dependency:list`                   | Lists all project dependencies.                             |
| `mvn dependency:analyze`                | Identifies unused or missing dependencies.                  |
| `mvn dependency:resolve`                | Downloads and resolves dependencies.                        |
| `mvn dependency:purge-local-repository` | Clears and re-downloads dependencies.                       |
| `mvn clean install -U`                  | Forces update of all snapshots.                             |
| `mvn dependency:copy-dependencies`      | Copies dependencies to the target folder.                   |

---

## 🧪 **Testing & Reporting**

| Command                                                         | Description                             |
| --------------------------------------------------------------- | --------------------------------------- |
| `mvn test -Dtest=ClassName`                                     | Runs a specific test class.             |
| `mvn test -Dtest=ClassName#methodName`                          | Runs a specific test method.            |
| `mvn test -Dgroups="regression"`                                | Runs a specific TestNG group.           |
| `mvn surefire-report:report`                                    | Generates a Surefire HTML test report.  |
| `mvn serenity:aggregate`                                        | Generates Serenity test reports (HTML). |
| `mvn verify -Dserenity.outputDirectory=target/serenity-reports` | Custom Serenity report location.        |

---

## ⚙️ **Customization**

| Command                                              | Description                           |
| ---------------------------------------------------- | ------------------------------------- |
| `mvn clean install -DskipTests`                      | Builds without running tests.         |
| `mvn clean install -Dmaven.test.failure.ignore=true` | Continues build even if tests fail.   |
| `mvn clean install -PprofileName`                    | Runs build using a specific profile.  |
| `mvn package -Denv=qa`                               | Uses custom environment variables.    |
| `mvn clean compile -X`                               | Enables debug logging.                |
| `mvn -T 1C clean install`                            | Builds using all available CPU cores. |
| `mvn --offline clean install`                        | Runs in offline mode.                 |

---

## 🧰 **Project Information**

| Command                                   | Description                                   |
| ----------------------------------------- | --------------------------------------------- |
| `mvn help:effective-pom`                  | Shows the complete, resolved POM file.        |
| `mvn help:effective-settings`             | Shows effective settings.xml (user + global). |
| `mvn help:active-profiles`                | Lists active Maven profiles.                  |
| `mvn help:system`                         | Lists system properties for Maven.            |
| `mvn versions:display-dependency-updates` | Shows newer dependency versions.              |
| `mvn versions:display-plugin-updates`     | Shows newer plugin versions.                  |

---

## 💡 **Real-World Examples**

| Command                                                     | Description                                     |
| ----------------------------------------------------------- | ----------------------------------------------- |
| `mvn clean install -U`                                      | Clean, build, and force dependency updates.     |
| `mvn clean verify serenity:aggregate`                       | Run all tests & generate Serenity reports.      |
| `mvn clean package -DskipTests`                             | Build artifacts without tests.                  |
| `mvn dependency:tree -Dincludes=com.fasterxml.jackson.core` | Show dependency details for a specific library. |
| `mvn test -Dtest=GenerateExtentReports`                     | Run a specific test class.                      |

---

### 🗂️ Recommended Folder for This File

`/docs/maven-commands.md`

---

**Tip:** Combine `mvn clean verify serenity:aggregate` to automatically produce Serenity reports after each test run.
