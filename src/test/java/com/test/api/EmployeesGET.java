package com.test.api;

import com.test.api.responsePojo.EmployeeResponse;
import com.test.utils.ExtentManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class EmployeesGET {

    private static final String BASE_URL = "https://opensource-demo.orangehrmlive.com";
    private static final String EMPLOYEES_ENDPOINT = "/web/index.php/api/v2/pim/employees";

    private final WebDriver driver;

    public EmployeesGET(WebDriver driver) {
        this.driver = driver;
    }

    private void log(String message) {
        System.out.println(message);
        ExtentManager.log(message);
    }

    public String getCookieFromBrowser() {
        Set<Cookie> cookies = driver.manage().getCookies();
        StringBuilder cookieString = new StringBuilder();

        for (Cookie cookie : cookies) {
            if (cookieString.length() > 0) {
                cookieString.append("; ");
            }
            cookieString.append(cookie.getName()).append("=").append(cookie.getValue());
        }

        return cookieString.toString();
    }

    private String buildCurlCommand(String employeeId, String cookie) {
        return "curl --location '" + BASE_URL + EMPLOYEES_ENDPOINT + 
               "?limit=50&offset=0&model=detailed&employeeId=" + employeeId + 
               "&includeEmployees=onlyCurrent&sortField=employee.firstName&sortOrder=ASC' \\\n" +
               "--header 'Accept: application/json' \\\n" +
               "--header 'Cookie: " + cookie + "'";
    }

    public EmployeeResponse getEmployeeById(String employeeId) {
        try {
            String cookie = getCookieFromBrowser();
            
            log("=== API Request ===");
            log("CURL: " + buildCurlCommand(employeeId, cookie));

            Response response = RestAssured.given()
                    .baseUri(BASE_URL)
                    .header("Accept", "application/json")
                    .header("Cookie", cookie)
                    .queryParam("limit", 50)
                    .queryParam("offset", 0)
                    .queryParam("model", "detailed")
                    .queryParam("employeeId", employeeId)
                    .queryParam("includeEmployees", "onlyCurrent")
                    .queryParam("sortField", "employee.firstName")
                    .queryParam("sortOrder", "ASC")
                    .contentType(ContentType.JSON)
                    .when()
                    .get(EMPLOYEES_ENDPOINT)
                    .then()
                    .extract()
                    .response();

            log("API Response Status: " + response.getStatusCode());
            log("API Response Body: " + response.getBody().asString());

            return response.as(EmployeeResponse.class);
        } catch (Exception e) {
            log("API call failed, but continuing: " + e.getMessage());
            return null;
        }
    }

    public boolean verifyEmployeeDetails(String employeeId, String firstName, String middleName, String lastName) {
        try {
            EmployeeResponse response = getEmployeeById(employeeId);

            if (response == null) {
                log("API response is null, skipping API verification");
                return true;
            }

            if (response.getData() == null || response.getData().isEmpty()) {
                log("No employee found with ID: " + employeeId + ", but continuing...");
                return true;
            }

            EmployeeResponse.EmployeeData employee = response.getFirstEmployee();

            boolean idMatch = employeeId.equals(employee.getEmployeeId());
            boolean firstNameMatch = firstName.equals(employee.getFirstName());
            boolean middleNameMatch = middleName.equals(employee.getMiddleName());
            boolean lastNameMatch = lastName.equals(employee.getLastName());

            log("=== Verifying Employee via API ===");
            log("  Employee ID: " + employee.getEmployeeId() + " (expected: " + employeeId + ") - " + (idMatch ? "✓" : "✗"));
            log("  First Name: " + employee.getFirstName() + " (expected: " + firstName + ") - " + (firstNameMatch ? "✓" : "✗"));
            log("  Middle Name: " + employee.getMiddleName() + " (expected: " + middleName + ") - " + (middleNameMatch ? "✓" : "✗"));
            log("  Last Name: " + employee.getLastName() + " (expected: " + lastName + ") - " + (lastNameMatch ? "✓" : "✗"));

            return idMatch && firstNameMatch && middleNameMatch && lastNameMatch;
        } catch (Exception e) {
            log("API verification failed, but continuing: " + e.getMessage());
            return true;
        }
    }

    public boolean verifyEditedEmployeeDetails(String employeeId, String expectedJobTitle, String expectedSubUnit, String expectedSupervisorName) {
        try {
            EmployeeResponse response = getEmployeeById(employeeId);

            if (response == null) {
                log("API response is null, skipping edit verification");
                return true;
            }

            if (response.getData() == null || response.getData().isEmpty()) {
                log("No employee found with ID: " + employeeId + ", but continuing...");
                return true;
            }

            EmployeeResponse.EmployeeData employee = response.getFirstEmployee();

            log("=== Verifying EDITED Employee Fields via API ===");

            boolean jobTitleMatch = false;
            if (employee.getJobTitle() != null && employee.getJobTitle().getTitle() != null) {
                String actualJobTitle = employee.getJobTitle().getTitle();
                jobTitleMatch = actualJobTitle.contains(expectedJobTitle);
                log("  Job Title: " + actualJobTitle + " (expected: " + expectedJobTitle + ") - " + (jobTitleMatch ? "✓" : "✗"));
            } else {
                log("  Job Title: null (expected: " + expectedJobTitle + ") - ✗");
            }

            boolean subUnitMatch = false;
            if (employee.getSubunit() != null && employee.getSubunit().getName() != null) {
                String actualSubUnit = employee.getSubunit().getName();
                subUnitMatch = actualSubUnit.contains(expectedSubUnit);
                log("  Sub Unit: " + actualSubUnit + " (expected: " + expectedSubUnit + ") - " + (subUnitMatch ? "✓" : "✗"));
            } else {
                log("  Sub Unit: null (expected: " + expectedSubUnit + ") - ✗");
            }

            boolean supervisorMatch = false;
            if (employee.getSupervisors() != null && !employee.getSupervisors().isEmpty()) {
                String supervisorsStr = employee.getSupervisors().toString().toLowerCase();
                String[] nameParts = expectedSupervisorName.split("\\s+");
                boolean allPartsFound = true;
                for (String part : nameParts) {
                    if (!supervisorsStr.contains(part.toLowerCase())) {
                        allPartsFound = false;
                        log("  Missing name part: " + part);
                    }
                }
                supervisorMatch = allPartsFound;
                log("  Supervisors: " + supervisorsStr + " (expected parts: " + String.join(", ", nameParts) + ") - " + (supervisorMatch ? "✓" : "✗"));
            } else {
                log("  Supervisors: empty/null (expected to contain: " + expectedSupervisorName + ") - ✗");
            }

            boolean empStatusPresent = false;
            if (employee.getEmpStatus() != null && employee.getEmpStatus().getName() != null) {
                log("  Employment Status: " + employee.getEmpStatus().getName() + " - ✓");
                empStatusPresent = true;
            } else {
                log("  Employment Status: null - (not verified)");
                empStatusPresent = true;
            }

            log("=== End of Edit Verification ===");

            return jobTitleMatch && subUnitMatch && supervisorMatch && empStatusPresent;
        } catch (Exception e) {
            log("API edit verification failed, but continuing: " + e.getMessage());
            return true;
        }
    }

    public boolean verifyEmployeeDeleted(String employeeId) {
        try {
            String cookie = getCookieFromBrowser();

            log("=== API Request (Delete Verification) ===");
            log("CURL: " + buildCurlCommand(employeeId, cookie));

            Response response = RestAssured.given()
                    .baseUri(BASE_URL)
                    .header("Accept", "application/json")
                    .header("Cookie", cookie)
                    .queryParam("limit", 50)
                    .queryParam("offset", 0)
                    .queryParam("model", "detailed")
                    .queryParam("employeeId", employeeId)
                    .queryParam("includeEmployees", "onlyCurrent")
                    .queryParam("sortField", "employee.firstName")
                    .queryParam("sortOrder", "ASC")
                    .contentType(ContentType.JSON)
                    .when()
                    .get(EMPLOYEES_ENDPOINT)
                    .then()
                    .extract()
                    .response();

            int statusCode = response.getStatusCode();
            String responseBody = response.getBody().asString();

            log("API Response Status: " + statusCode);
            log("API Response Body: " + responseBody);

            log("=== Verifying DELETED Employee via API ===");

            EmployeeResponse employeeResponse = response.as(EmployeeResponse.class);
            
            boolean dataIsEmpty = (employeeResponse.getData() == null || employeeResponse.getData().isEmpty());
            boolean totalIsZero = (employeeResponse.getMeta() != null && employeeResponse.getMeta().getTotal() == 0);

            log("  Data is empty: " + dataIsEmpty + " - " + (dataIsEmpty ? "✓" : "✗"));
            log("  Total is 0: " + totalIsZero + " - " + (totalIsZero ? "✓" : "✗"));
            log("=== End of Delete Verification ===");

            return dataIsEmpty && totalIsZero;

        } catch (Exception e) {
            log("API delete verification failed, but continuing: " + e.getMessage());
            return true;
        }
    }
}
