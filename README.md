# SeleniumWithJava – Test Automation Framework

A scalable and maintainable Selenium test automation framework built using Java, TestNG, Maven, and Page Object Model (POM) design pattern. The framework supports YAML-based test data management, Extent Reports for rich test reporting, and follows best practices used in real-world automation projects.

## 📌 Key Features

* Page Object Model (POM) for better maintainability and reusability
* TestNG for test execution, grouping, and parallel runs
* Extent Reports for detailed HTML test reports
* YAML files for externalized test data management
* Maven for build and dependency management
* Cross-browser execution support
* GitHub version control for CI/CD readiness

## 📁 Project Structure

```
SeleniumWithJava/
├── src/
│   ├── main/
│   │   └── resources/
│   │       └── *.yaml                 # Test data files
│   └── test/
│       ├── java/
│       │   ├── base/                  # Base test setup & teardown
│       │   ├── pages/                 # Page Object classes
│       │   ├── tests/                 # Test classes
│       │   └── utils/                 # Utilities (Driver, Config, Reports)
├── reports/                           # Extent HTML reports
├── pom.xml                            # Maven dependencies
├── testng.xml                         # TestNG suite configuration
└── README.md
```

## 🛠️ Technologies Used

* Java
* Selenium WebDriver
* TestNG
* Maven
* Extent Reports
* YAML
* Git & GitHub

## ⚙️ Framework Components

### 🔹 Base Layer

* Handles WebDriver initialization
* Manages browser setup and teardown
* Loads configuration and test data

### 🔹 Page Layer (POM)

* Each web page is represented as a separate class
* Web elements and actions are encapsulated
* Improves test readability and reduces duplication

### 🔹 Test Layer

* Contains TestNG test cases
* Validations and assertions
* Test grouping and execution control

### 🔹 Utilities

* Driver factory
* YAML data reader
* Extent report manager
* Common reusable methods

## 📊 Reporting (Extent Reports)

* Generates detailed HTML reports after execution
* Includes:
   * Test steps
   * Pass/Fail status
   * Screenshots on failure
   * Execution time

Reports are stored in the `reports/` directory.

## 🚀 Execution

Run all tests:

```
mvn test
```

Run using TestNG suite:

```
mvn test -DsuiteXmlFile=testng.xml
```

## 🤝 Contribution & Version Control

* Code managed using GitHub
* Follows feature-branch workflow
* Supports CI/CD integration (Jenkins/GitHub Actions)

## 🏗️ Framework Architecture Diagram – Description

You can add this below a diagram image (or even without an image):

### Framework Architecture Flow

1. TestNG Test Classes
   * Trigger test execution
   * Call respective Page Object methods
2. Page Object Layer (POM)
   * Contains locators and page actions
   * Interacts with WebDriver only via Base classes
3. Base & Utility Layer
   * Initializes WebDriver
   * Reads YAML test data
   * Handles waits, screenshots, and reporting
4. WebDriver (Selenium)
   * Executes browser actions
   * Communicates with real browsers (Chrome/Firefox/Edge)
5. Extent Reports
   * Captures execution results
   * Generates interactive HTML reports

Flow: `TestNG Tests → Page Objects → Selenium WebDriver → Browser → Extent Reports`
