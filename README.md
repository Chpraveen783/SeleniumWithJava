# SeleniumWithJava

A Java-based project for web automation and testing using **Selenium WebDriver**. This repository contains examples and test scripts that demonstrate how to interact with web applications programmatically using Java and Selenium.

---

## 📁 Project Structure

```
SeleniumWithJava/
├── src/                   # Source code (test scripts & utilities)
├── .idea/                 # IntelliJ IDEA project settings
├── .gitignore             # Git ignore rules
└── pom.xml                # Maven project configuration & dependencies
```

---

## 🛠️ Technologies Used

- **Java** — Programming language
- **Selenium WebDriver** — Browser automation framework
- **Maven** — Build and dependency management tool
- **IntelliJ IDEA** — IDE (recommended)

---

## ⚙️ Prerequisites

Before running this project, make sure you have the following installed:

1. **Java JDK** (version 8 or above)  
   → [Download JDK](https://www.oracle.com/java/technologies/javase-downloads.html)

2. **Maven**  
   → [Download Maven](https://maven.apache.org/download.cgi)

3. **A web browser** (Chrome / Firefox / Edge)  
   → Make sure the corresponding **WebDriver** is installed and available in your system PATH.

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/Chpraveen783/SeleniumWithJava.git
cd SeleniumWithJava
```

### 2. Open in IDE

Open the project in **IntelliJ IDEA** or any Maven-compatible IDE. Allow it to import the `pom.xml` dependencies automatically.

### 3. Install Dependencies

```bash
mvn install
```

### 4. Run Tests

```bash
mvn test
```

Or run individual test classes directly from your IDE.

---

## 🧪 WebDriver Setup

Make sure your browser driver matches your installed browser version:

| Browser | Driver |
|-----------|--------------------------|
| Chrome | ChromeDriver |
| Firefox | GeckoDriver |
| Edge | EdgeDriver |

> **Tip:** Place the driver executable in your system PATH or configure the driver path in your test scripts.

---

## 📝 Usage

Each test class in the `src` folder demonstrates a specific Selenium use case such as:

- Launching and navigating browsers
- Locating web elements (By ID, Name, XPath, CSS Selector, etc.)
- Filling forms and interacting with dropdowns
- Handling waits (Implicit & Explicit)
- Assertions and validations

---

## 🤝 Contributing

Contributions are welcome! Feel free to:

1. Fork the repository
2. Create a new branch (`feature/your-feature`)
3. Commit your changes
4. Push and open a **Pull Request**

---

> **Author:** [Chpraveen783](https://github.com/Chpraveen783)
