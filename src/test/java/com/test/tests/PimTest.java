package com.test.tests;

import com.test.api.EmployeesGET;
import com.test.base.BaseTest;
import com.test.data.TestData;
import com.test.pages.*;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class PimTest extends BaseTest {

    private static String sharedEmployeeId;
    private static String sharedFirstName;
    private static String sharedMiddleName;
    private static String sharedLastName;

    @Test(dataProvider = "testData", groups = {"pim", "smoke"}, description = "Add new employee with random name and verify in employee list")
    public void addEmployeeAndVerifyInList(TestData data, ITestContext context) {

        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        NavBarSidePage navBarSide = new NavBarSidePage(driver);
        PimPage pimPage = new PimPage(driver);
        EmployeesGET employeesGET = new EmployeesGET(driver);

        String username = data.get("userName");
        String password = data.get("password");

        step("Login to the application");
        loginPage.login(username, password);
        Assert.assertTrue(dashboardPage.isUserLoggedIn(), "User should be logged in");

        step("Navigate to PIM page");
        navBarSide.open(NavBarSidePage.MenuItem.PIM);
        pimPage.pimPageIsLoaded();

        step("Click Add Employee and fill in random name");
        pimPage.clickAddEmployee();

        step("Set UUID and generate random full name");
        pimPage.setUuidEmployeeId();
        pimPage.generateRandomFullName();
        pimPage.uploadPlaceholderImage();

        String firstName = pimPage.getGeneratedFirstName();
        String middleName = pimPage.getGeneratedMiddleName();
        String lastName = pimPage.getGeneratedLastName();
        String expectedFullName = pimPage.getGeneratedFullName();
        String expectedDisplayName = firstName + " " + lastName;

        log("Generated Name: " + expectedFullName);

        step("Save the employee");
        boolean saved = pimPage.saveEmployeeWithRetry();
        Assert.assertTrue(saved, "Employee should be saved successfully");

        String employeeId = pimPage.getEmployeeId();
        log("Final Employee ID: " + employeeId);

        sharedEmployeeId = employeeId;
        sharedFirstName = firstName;
        sharedMiddleName = middleName;
        sharedLastName = lastName;

        context.setAttribute("employeeId", employeeId);
        context.setAttribute("firstName", firstName);
        context.setAttribute("middleName", middleName);
        context.setAttribute("lastName", lastName);

        pimPage.waitForPersonalDetailsPage();

        step("Verify employee name on Personal Details page");
        String displayedEmployeeName = pimPage.getEmployeeNameText();
        Assert.assertEquals(displayedEmployeeName, expectedDisplayName,
                "Employee name on Personal Details page should match");

        String actualFirstName = pimPage.getFirstNameValue();
        String actualMiddleName = pimPage.getMiddleNameValue();
        String actualLastName = pimPage.getLastNameValue();

        Assert.assertEquals(actualFirstName, firstName, "First name should match");
        Assert.assertEquals(actualMiddleName, middleName, "Middle name should match");
        Assert.assertEquals(actualLastName, lastName, "Last name should match");

        step("Navigate to Employee List and search by Employee ID");
        pimPage.searchEmployeeById(employeeId);

        step("Verify employee appears in search results");
        Assert.assertTrue(pimPage.isEmployeeIdInTable(employeeId),
                "Employee ID should appear in table");
        Assert.assertTrue(pimPage.isFirstMiddleNameInTable(firstName, middleName),
                "First and Middle name should appear in table");
        Assert.assertTrue(pimPage.isLastNameInTable(lastName),
                "Last name should appear in table");

        step("Verify employee via API");
        log("Calling API to verify employee data...");
        boolean apiVerified = employeesGET.verifyEmployeeDetails(employeeId, firstName, middleName, lastName);
        Assert.assertTrue(apiVerified, "Employee details should match via API verification");

        step("Employee successfully added and verified via UI and API");
    }

    @Test(dataProvider = "testData", groups = {"pim", "smoke"},
            dependsOnMethods = "addEmployeeAndVerifyInList",
            description = "Edit employee details and verify in employee list")
    public void editEmployeeAndVerifyInList(TestData data, ITestContext context) {

        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        PimPage pimPage = new PimPage(driver);
        EmployeesGET employeesGET = new EmployeesGET(driver);

        String username = data.get("userName");
        String password = data.get("password");
        String employeeListUrl = data.get("employeeListUrl");
        String otherId = data.get("otherId");
        String licenseNumber = data.get("licenseNumber");
        String supervisorName = data.get("supervisorName");

        String employeeId = sharedEmployeeId;
        if (employeeId == null) {
            employeeId = (String) context.getAttribute("employeeId");
        }
        Assert.assertNotNull(employeeId, "Employee ID should be available from previous test");
        log("Using Employee ID from previous test: " + employeeId);

        step("Check login status and navigate to Employee List");
        loginPage.login(username, password);
        Assert.assertTrue(dashboardPage.isUserLoggedIn(), "User should be logged in");

        driver.get(employeeListUrl);
        pimPage.waitForEmployeeInformationPage();

        step("Search for employee by ID and click Edit");
        pimPage.inputEmployeeIdSearch(employeeId);
        pimPage.clickSearch();
        pimPage.waitForTableRows(employeeId);
        pimPage.clickEditButton();
        pimPage.waitForPersonalDetailsPage();

        step("Edit Personal Details");
        pimPage.editPersonalDetails(otherId, licenseNumber);
        log("Other ID: " + pimPage.getSavedOtherId());
        log("License Number: " + pimPage.getSavedLicenseNumber());
        log("Gender: " + pimPage.getSavedGender());

        step("Edit Job Details");
        pimPage.editJobDetails();
        log("Job Title: " + pimPage.getSavedJobTitle());
        log("Sub Unit: " + pimPage.getSavedSubUnit());

        step("Add Supervisor");
        pimPage.addSupervisor(supervisorName);
        log("Supervisor: " + pimPage.getSavedSupervisorName());

        step("Navigate to Employee List and verify updated details");
        driver.get(employeeListUrl);
        pimPage.waitForEmployeeInformationPage();
        pimPage.inputEmployeeIdSearch(employeeId);
        pimPage.clickSearch();
        pimPage.waitForTableRows(employeeId);

        step("Verify Job Title in table");
        String jobTitleInTable = pimPage.getTableCellText(5);
        log("Job Title in table: " + jobTitleInTable);
        Assert.assertTrue(jobTitleInTable.contains("QA Engineer"),
                "Job Title should be QA Engineer");

        step("Verify Sub Unit in table");
        String subUnitInTable = pimPage.getTableCellText(7);
        log("Sub Unit in table: " + subUnitInTable);
        Assert.assertTrue(subUnitInTable.contains("Quality Assurance"),
                "Sub Unit should be Quality Assurance");

        step("Verify Supervisor in table");
        String supervisorInTable = pimPage.getTableCellText(8);
        log("Supervisor in table: " + supervisorInTable);
        Assert.assertTrue(supervisorInTable.toLowerCase().contains(supervisorName.toLowerCase()),
                "Supervisor should contain: " + supervisorName);

        step("Verify edited fields via API");
        String expectedJobTitle = pimPage.getSavedJobTitle();
        String expectedSubUnit = pimPage.getSavedSubUnit();
        String actualSupervisorName = pimPage.getSavedSupervisorName();
        log("Using actual supervisor name from dropdown: " + actualSupervisorName);
        log("Calling API to verify edited employee data...");
        boolean apiVerified = employeesGET.verifyEditedEmployeeDetails(
                employeeId,
                expectedJobTitle,
                expectedSubUnit,
                actualSupervisorName
        );
        Assert.assertTrue(apiVerified, "Edited employee details should match via API verification");

        step("Employee successfully edited and verified via UI and API");
    }

    @Test(dataProvider = "testData", groups = {"pim", "smoke"},
            dependsOnMethods = "editEmployeeAndVerifyInList",
            alwaysRun = true,
            description = "Delete employee and verify deletion")
    public void deleteEmployeeAndVerify(TestData data, ITestContext context) {

        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        PimPage pimPage = new PimPage(driver);
        EmployeesGET employeesGET = new EmployeesGET(driver);
        NavBarTopPage navBarTopPage = new NavBarTopPage(driver);

        String username = data.get("userName");
        String password = data.get("password");
        String employeeListUrl = data.get("employeeListUrl");

        String employeeId = sharedEmployeeId;
        if (employeeId == null) {
            employeeId = (String) context.getAttribute("employeeId");
        }
        Assert.assertNotNull(employeeId, "Employee ID should be available from previous test");
        log("Deleting Employee ID: " + employeeId);

        step("Login and navigate to Employee List");
        loginPage.login(username, password);
        Assert.assertTrue(dashboardPage.isUserLoggedIn(), "User should be logged in");

        driver.get(employeeListUrl);
        pimPage.waitForEmployeeInformationPage();

        step("Search for employee by ID");
        pimPage.inputEmployeeIdSearch(employeeId);
        pimPage.clickSearch();
        pimPage.waitForTableRows(employeeId);
        pimPage.r

        step("Click Delete and confirm");
        pimPage.clickDeleteButton();
        pimPage.confirmDelete();

        step("Wait for delete completion (No Records Found)");
        pimPage.waitForDeleteCompletion();

        step("Verify deletion via API");
        log("Calling API to verify employee deletion...");
        boolean apiVerified = employeesGET.verifyEmployeeDeleted(employeeId);
        Assert.assertTrue(apiVerified, "API should return empty data array and total=0 for deleted employee");

        step("Employee successfully deleted and verified via UI and API");

        step("Logout");
        navBarTopPage.logout();
        Assert.assertTrue(loginPage.waitForPageToLoad(), "Ensure user is returned back to login page");
    }
}
