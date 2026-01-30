# Selenium TestNG Automation Framework

A robust, data-driven test automation framework built with Selenium WebDriver, TestNG, and Extent Reports.
Demo: https://drive.google.com/file/d/1Qe8pOf5h4snKDqcowCm-PgnZzL9Pvldu/view?usp=sharing

## Prerequisites

- Java 17+
- Maven 3.6+
- Chrome browser

## Setup Instructions

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd Take-Home-Assessement-Compare-Club
   ```

2. **Install dependencies**
   ```bash
   mvn clean install -DskipTests
   ```

3. **Run tests**
   ```bash
   mvn test
   ```

## Framework Structure

```
src/
├── main/
│   └── resources/
│       ├── Testdata.json          # Test data for data-driven testing
│       └── placeholder_image.jpg  # Test assets
│
└── test/
    └── java/com/test/
        ├── base/
        │   └── BaseTest.java      # Test setup, teardown, WebDriver config
        │
        ├── core/
        │   └── Locator.java       # Custom locator wrapper with names
        │
        ├── pages/
        │   ├── BasePage.java      # Common page actions (click, type, wait)
        │   ├── LoginPage.java     # Login page object
        │   ├── DashboardPage.java # Dashboard page object
        │   ├── PimPage.java       # PIM module page object
        │   └── NavBar*.java       # Navigation components
        │
        ├── tests/
        │   ├── LoginPageTest.java # Login test cases
        │   └── PimTest.java       # Employee management tests
        │
        ├── api/
        │   ├── EmployeesGET.java  # API client for verification
        │   └── responsePojo/      # Response models
        │
        └── utils/
            ├── ExtentManager.java       # Report management
            ├── ExtentReportListener.java # TestNG listener
            └── TestDataProvider.java    # JSON data provider
```

## How to Run Tests

**Run all tests:**
```bash
mvn test
```

**Run specific test class:**
```bash
mvn test -Dtest=PimTest
```

**Run specific test method:**
```bash
mvn test -Dtest=PimTest#addEmployeeAndVerifyInList
```

**Run by test group:**
```bash
mvn test -Dgroups=smoke
```

## Test Reports

After test execution, open the generated report:
```
extent-report.html
```

Features:
- Step-by-step execution logs
- Screenshots on failure (embedded inline)
- Pass/Fail status with assertions

## Key Features

| Feature | Description |
|---------|-------------|
| **Page Object Model** | Clean separation of locators, actions, and tests |
| **Data-Driven Testing** | Test data externalized in JSON |
| **Custom Locators** | Named locators for better error messages |
| **Extent Reports** | Rich HTML reports with screenshots |
| **API Verification** | REST Assured integration for backend validation |
| **Auto-Retry** | Resilient handling of flaky elements |
| **Test Dependencies** | Sequential test execution (Add → Edit → Delete) |

## Dependencies

| Dependency | Version | Purpose |
|------------|---------|---------|
| Selenium | 4.17.0 | Browser automation |
| TestNG | 7.9.0 | Test framework |
| WebDriverManager | 5.6.3 | Automatic driver management |
| Extent Reports | 5.1.1 | HTML reporting |
| REST Assured | 5.4.0 | API testing |
| Gson | 2.10.1 | JSON parsing |
| Lombok | 1.18.30 | Reduce boilerplate code |

## Test Scenarios

### PIM Module Tests
1. **Add Employee** - Create employee with random data, verify via UI and API
2. **Edit Employee** - Update job details, supervisor, verify changes
3. **Delete Employee** - Remove employee, confirm deletion via UI and API

### Login Tests
- Successful login with valid credentials

## Configuration

Test data is managed in `src/main/resources/Testdata.json`:
```json
{
  "testMethodName": "addEmployeeAndVerifyInList",
  "scenario": "Add new employee with random name",
  "shouldRun": true,
  "testData": {
    "userName": "Admin",
    "password": "admin123"
  }
}
```

---

*Built for OrangeHRM Demo Application*

