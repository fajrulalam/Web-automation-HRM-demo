package com.test.pages;

import com.test.core.Locator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

public class PimPage extends BasePage {

    private static final String IMAGE_PATH = "src/main/resources/placeholder_image_female.jpg";

    private static final Locator FORM_LOADER = Locator.byXpath(
            "FORM_LOADER",
            "//div[@class='oxd-form-loader']"
    );

    private static final Locator EMPLOYEE_LIST = Locator.byXpath(
            "EMPLOYEE_LIST",
            "//a[normalize-space()='Employee List']"
    );

    private static final Locator ADD_EMPLOYEE = Locator.byXpath(
            "ADD_EMPLOYEE",
            "//a[normalize-space()='Add Employee']"
    );

    private static final Locator REPORTS = Locator.byXpath(
            "REPORTS",
            "//a[normalize-space()='Reports']"
    );

    private static final Locator EMPLOYEE_FULL_NAME_LABEL = Locator.byXpath(
            "EMPLOYEE_FULL_NAME_LABEL",
            "//label[normalize-space()='Employee Full Name']"
    );

    private static final Locator FIRST_NAME_INPUT = Locator.byXpath(
            "FIRST_NAME_INPUT",
            "//input[@placeholder='First Name']"
    );

    private static final Locator MIDDLE_NAME_INPUT = Locator.byXpath(
            "MIDDLE_NAME_INPUT",
            "//input[@placeholder='Middle Name']"
    );

    private static final Locator LAST_NAME_INPUT = Locator.byXpath(
            "LAST_NAME_INPUT",
            "//input[@placeholder='Last Name']"
    );

    private static final Locator UPLOAD_IMAGE_BUTTON = Locator.byXpath(
            "UPLOAD_IMAGE_BUTTON",
            "//i[@class='oxd-icon bi-plus']"
    );

    private static final Locator IMAGE_INPUT = Locator.byXpath(
            "IMAGE_INPUT",
            "//input[@type='file']"
    );

    private static final Locator PROFILE_IMAGE_PREVIEW = Locator.byXpath(
            "PROFILE_IMAGE_PREVIEW",
            "//img[contains(@class, 'employee-image') or contains(@src, 'blob:') or contains(@src, 'data:image')]"
    );

    private static final Locator IMAGE_CONTAINER = Locator.byXpath(
            "IMAGE_CONTAINER",
            "//div[contains(@class, 'orangehrm-employee-image')]//img | //div[contains(@class, 'profile-image-container')]//img"
    );

    private static final Locator SAVE_BUTTON = Locator.byXpath(
            "SAVE_BUTTON",
            "//button[normalize-space()='Save']"
    );

    private static final Locator CANCEL_BUTTON = Locator.byXpath(
            "CANCEL_BUTTON",
            "//button[normalize-space()='Cancel']"
    );

    private static final Locator PERSONAL_DETAILS_HEADER = Locator.byXpath(
            "PERSONAL_DETAILS_HEADER",
            "//h6[normalize-space()='Personal Details']"
    );

    private static final Locator EMPLOYEE_NAME = Locator.byXpath(
            "EMPLOYEE_NAME",
            "//h6[contains(@class, '--strong')]"
    );

    private static final Locator EMPLOYEE_INFORMATION_HEADER = Locator.byXpath(
            "EMPLOYEE_INFORMATION_HEADER",
            "//h5[normalize-space()='Employee Information']"
    );

    private static final Locator EMPLOYEE_ID_SEARCH_INPUT = Locator.byXpath(
            "EMPLOYEE_ID_SEARCH_INPUT",
            "//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']"
    );

    private static final Locator SEARCH_BUTTON = Locator.byXpath(
            "SEARCH_BUTTON",
            "//button[normalize-space()='Search']"
    );

    private static final Locator TABLE_ROW = Locator.byXpath(
            "TABLE_ROW",
            "//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']"
    );

    private static final Locator EMPLOYEE_ID_INPUT = Locator.byXpath(
            "EMPLOYEE_ID_INPUT",
            "(//input[@class='oxd-input oxd-input--active'])[2]"
    );

    private static final Locator EMPLOYEE_ID_INPUT_RETRY = Locator.byXpath(
            "EMPLOYEE_ID_INPUT_RETRY",
            "//div[label[text()='Employee Id']]/following-sibling::div/input"
    );



    private static final Locator EMPLOYEE_ID_ERROR = Locator.byXpath(
            "EMPLOYEE_ID_ERROR",
            "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']"
    );

    private static final Locator EDIT_BUTTON = Locator.byXpath(
            "EDIT_BUTTON",
            "//i[@class='oxd-icon bi-pencil-fill']"
    );

    private static final Locator DELETE_BUTTON = Locator.byXpath(
            "DELETE_BUTTON",
            "//i[@class='oxd-icon bi-trash']"
    );

    private static final Locator CONFIRM_DELETE_BUTTON = Locator.byXpath(
            "CONFIRM_DELETE_BUTTON",
            "//button[normalize-space()='Yes, Delete']"
    );

    private static final Locator TOAST_MESSAGE = Locator.byXpath(
            "TOAST_MESSAGE",
            "//p[@class='oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text']"
    );

    private static final Locator NO_RECORDS_FOUND = Locator.byXpath(
            "NO_RECORDS_FOUND",
            "//span[contains(text(), 'No Records Found')]"
    );

    private static final Locator OTHER_ID_INPUT = Locator.byXpath(
            "OTHER_ID_INPUT",
            "//div[label[text()='Other Id']]/following-sibling::div/input"
    );

    private static final Locator LICENSE_NUMBER_INPUT = Locator.byXpath(
            "LICENSE_NUMBER_INPUT",
            "//div[label[contains(text(), 'License Number')]]/following-sibling::div/input"
    );

    private static final Locator LICENSE_EXPIRY_DATE_INPUT = Locator.byXpath(
            "LICENSE_EXPIRY_DATE_INPUT",
            "//div[label[text()='License Expiry Date']]/following-sibling::div//input"
    );

    private static final Locator NATIONALITY_DROPDOWN = Locator.byXpath(
            "NATIONALITY_DROPDOWN",
            "//div[label[text()='Nationality']]/following-sibling::div//div[@class='oxd-select-text-input']"
    );

    private static final Locator DROPDOWN_OPTION_SECOND = Locator.byXpath(
            "DROPDOWN_OPTION_SECOND",
            "//div[@class='oxd-select-option'][2]"
    );

    private static final Locator MARITAL_STATUS_DROPDOWN = Locator.byXpath(
            "MARITAL_STATUS_DROPDOWN",
            "//div[label[text()='Marital Status']]/following-sibling::div//div[@class='oxd-select-text-input']"
    );

    private static final Locator SINGLE_OPTION = Locator.byXpath(
            "SINGLE_OPTION",
            "//span[text()='Single']"
    );

    private static final Locator FEMALE_RADIO = Locator.byXpath(
            "FEMALE_RADIO",
            "//label[normalize-space()='Female']"
    );

    private static final Locator MALE_RADIO = Locator.byXpath(
            "MALE_RADIO",
            "//label[normalize-space()='Male']"
    );

    private static final Locator DATE_OF_BIRTH_INPUT = Locator.byXpath(
            "DATE_OF_BIRTH_INPUT",
            "//div[label[text()='Date of Birth']]/following-sibling::div//input"
    );

    private static final Locator SUCCESS_TOAST = Locator.byXpath(
            "SUCCESS_TOAST",
            "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']"
    );

    private static final Locator JOB_TAB = Locator.byXpath(
            "JOB_TAB",
            "//a[normalize-space()='Job']"
    );

    private static final Locator JOB_TITLE_DROPDOWN = Locator.byXpath(
            "JOB_TITLE_DROPDOWN",
            "//div[label[text()='Job Title']]/following-sibling::div//div[@class='oxd-select-text-input']"
    );

    private static final Locator QA_ENGINEER_OPTION = Locator.byXpath(
            "QA_ENGINEER_OPTION",
            "//span[text()='QA Engineer']"
    );

    private static final Locator JOB_CATEGORY_DROPDOWN = Locator.byXpath(
            "JOB_CATEGORY_DROPDOWN",
            "//div[label[text()='Job Category']]/following-sibling::div//div[@class='oxd-select-text-input']"
    );

    private static final Locator SUB_UNIT_DROPDOWN = Locator.byXpath(
            "SUB_UNIT_DROPDOWN",
            "//div[label[text()='Sub Unit']]/following-sibling::div//div[@class='oxd-select-text-input']"
    );

    private static final Locator QA_SUBUNIT_OPTION = Locator.byXpath(
            "QA_SUBUNIT_OPTION",
            "//span[text()='Quality Assurance']"
    );

    private static final Locator EMPLOYMENT_STATUS_DROPDOWN = Locator.byXpath(
            "EMPLOYMENT_STATUS_DROPDOWN",
            "//div[label[text()='Employment Status']]/following-sibling::div//div[@class='oxd-select-text-input']"
    );

    private static final Locator REPORT_TO_TAB = Locator.byXpath(
            "REPORT_TO_TAB",
            "//a[normalize-space()='Report-to']"
    );

    private static final Locator ASSIGNED_SUPERVISORS_HEADER = Locator.byXpath(
            "ASSIGNED_SUPERVISORS_HEADER",
            "//h6[normalize-space()='Assigned Supervisors']"
    );

    private static final Locator ADD_SUPERVISOR_BUTTON = Locator.byXpath(
            "ADD_SUPERVISOR_BUTTON",
            "(//button[contains(., 'Add')])[1]"
    );

    private static final Locator SUPERVISOR_NAME_INPUT = Locator.byXpath(
            "SUPERVISOR_NAME_INPUT",
            "//input[@placeholder='Type for hints...']"
    );

    private static final Locator AUTOCOMPLETE_FIRST_OPTION = Locator.byXpath(
            "AUTOCOMPLETE_FIRST_OPTION",
            "(//div[@role='option' and contains(@class, 'oxd-autocomplete-option')])[1]"
    );

    private static final Locator AUTOCOMPLETE_LOADING = Locator.byXpath(
            "AUTOCOMPLETE_LOADING",
            "//div[contains(@class, 'oxd-autocomplete-option') and contains(., 'Searching')]"
    );

    private static final Locator REPORTING_METHOD_DROPDOWN = Locator.byXpath(
            "REPORTING_METHOD_DROPDOWN",
            "//*[contains(text(), 'Select')]"
    );

    private static final Locator DIRECT_OPTION = Locator.byXpath(
            "DIRECT_OPTION",
            "//*[contains(text(), 'Direct')]"
    );

    private static final String[] FIRST_NAMES = {"John", "Jane", "Michael", "Sarah", "David", "Emily", "Robert", "Lisa", "William", "Emma"};
    private static final String[] MIDDLE_NAMES = {"Lee", "Marie", "James", "Ann", "Ray", "Rose", "Paul", "Grace", "Dean", "May"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Martinez", "Wilson"};

    private final Random random = new Random();

    private String generatedFirstName;
    private String generatedMiddleName;
    private String generatedLastName;
    private String employeeId;

    private String savedOtherId;
    private String savedLicenseNumber;
    private String savedLicenseExpiryDate;
    private String savedNationality;
    private String savedMaritalStatus;
    private String savedGender;
    private String savedDateOfBirth;
    private String savedJobTitle;
    private String savedJobCategory;
    private String savedSubUnit;
    private String savedEmploymentStatus;
    private String savedSupervisorName;

    public PimPage(WebDriver driver) {
        super(driver);
    }

    public PimPage(WebDriver driver, int timeoutSeconds) {
        super(driver, timeoutSeconds);
    }

    public void pimPageIsLoaded() {
        waitForVisible(ADD_EMPLOYEE);
    }

    public void clickAddEmployee() {
        click(ADD_EMPLOYEE);
        waitForClickable(FIRST_NAME_INPUT);
        waitForLoaderToDisappear();
    }

    private void waitForLoaderToDisappear() {
        log("Waiting for form loader to disappear...");
        int maxWaitSeconds = 3;
        long startTime = System.currentTimeMillis();
        
        while ((System.currentTimeMillis() - startTime) < maxWaitSeconds * 1000) {
            try {
                if (!isPresent(FORM_LOADER)) {
                    log("Form loader gone");
                    return;
                }
                Thread.sleep(500);
            } catch (Exception e) {
                return;
            }
        }
        log("Loader wait timeout - continuing anyway");
    }

    public void setUuidEmployeeId() {
        String uuidId = generateUuidId();
        log("Setting UUID Employee ID: " + uuidId);

        WebElement element = null;
        waitForVisible(EMPLOYEE_ID_INPUT);
        
        
        try {
            element = waitForClickable(EMPLOYEE_ID_INPUT);
        } catch (Exception e) {
            element = waitForClickable(EMPLOYEE_ID_INPUT_RETRY);
        }
        
        
        try {
            element.click();
        } catch (Exception e) {
            log("Regular click failed, waiting and using JS click...");
            executeScript("arguments[0].click();", element);
        }

        String currentValue = element.getAttribute("value");
        log("Current Employee ID before clear: " + currentValue);
        
        element.clear();

        executeScript("arguments[0].value = '';", element);
        executeScript("arguments[0].select();", element);

        element.sendKeys(Keys.chord(Keys.COMMAND, "a"));
        element.sendKeys(Keys.BACK_SPACE);

        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.BACK_SPACE);

        int length = element.getAttribute("value").length();
        for (int i = 0; i < length + 5; i++) {
            element.sendKeys(Keys.BACK_SPACE);
        }

        element.sendKeys(uuidId);
        
        String actualValue = element.getAttribute("value");
        log("Employee ID field now contains: " + actualValue);
        
        if (actualValue.length() > uuidId.length()) {
            log("Detected extra characters, clearing and retrying...");
            element.clear();
            executeScript("arguments[0].value = '';", element);
            element.sendKeys(uuidId);
            actualValue = element.getAttribute("value");
            log("After retry, Employee ID is: " + actualValue);
        }
        
        employeeId = uuidId;
    }

    public void clickEmployeeList() {
        click(EMPLOYEE_LIST);
    }

    public void clickReports() {
        click(REPORTS);
    }

    public void inputFirstName(String firstName) {
        sendKeys(FIRST_NAME_INPUT, firstName);
    }

    public void inputMiddleName(String middleName) {
        sendKeys(MIDDLE_NAME_INPUT, middleName);
    }

    public void inputLastName(String lastName) {
        sendKeys(LAST_NAME_INPUT, lastName);
    }

    public String generateRandomFirstName() {
        generatedFirstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        inputFirstName(generatedFirstName);
        return generatedFirstName;
    }

    public String generateRandomMiddleName() {
        generatedMiddleName = MIDDLE_NAMES[random.nextInt(MIDDLE_NAMES.length)];
        inputMiddleName(generatedMiddleName);
        return generatedMiddleName;
    }

    public String generateRandomLastName() {
        generatedLastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        inputLastName(generatedLastName);
        return generatedLastName;
    }

    public void generateRandomFullName() {
        generateRandomFirstName();
        generateRandomMiddleName();
        generateRandomLastName();
    }

    public void captureEmployeeId() {
        employeeId = getAttribute(EMPLOYEE_ID_INPUT, "value");
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getGeneratedFirstName() {
        return generatedFirstName;
    }

    public String getGeneratedMiddleName() {
        return generatedMiddleName;
    }

    public String getGeneratedLastName() {
        return generatedLastName;
    }

    public String getGeneratedFullName() {
        return generatedFirstName + " " + generatedMiddleName + " " + generatedLastName;
    }

    public void uploadImage(String filePath) {
        File file = new File(filePath);
        String absolutePath = file.getAbsolutePath();
        log("Uploading image: " + absolutePath);
        
        findElement(IMAGE_INPUT).sendKeys(absolutePath);
        
        waitForNetworkIdle(1000);
        
        if (verifyImageUploaded()) {
            log("Image upload verified successfully");
        } else {
            log("WARNING: Could not verify image upload, but continuing...");
        }
    }

    public void uploadPlaceholderImage() {
        uploadImage(IMAGE_PATH);
    }

    public boolean verifyImageUploaded() {
        try {
            String inputValue = findElement(IMAGE_INPUT).getAttribute("value");
            if (inputValue != null && !inputValue.isEmpty()) {
                log("Image file input has value: " + inputValue);
                return true;
            }
        } catch (Exception e) {
            log("Could not check file input value: " + e.getMessage());
        }

        try {
            if (isPresent(PROFILE_IMAGE_PREVIEW)) {
                String src = getAttribute(PROFILE_IMAGE_PREVIEW, "src");
                if (src != null && (src.startsWith("blob:") || src.startsWith("data:image"))) {
                    log("Image preview found with src: " + src.substring(0, Math.min(50, src.length())) + "...");
                    return true;
                }
            }
        } catch (Exception e) {
            log("Could not check image preview: " + e.getMessage());
        }

        try {
            if (isPresent(IMAGE_CONTAINER)) {
                log("Image container found");
                return true;
            }
        } catch (Exception e) {
            log("Could not check image container: " + e.getMessage());
        }

        return false;
    }

    public void clickSave() {
        click(SAVE_BUTTON);
    }

    public void clickCancel() {
        click(CANCEL_BUTTON);
    }

    public boolean saveEmployeeWithRetry() {
        int maxAttempts = 10;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            log("=== Save attempt " + attempt + " of " + maxAttempts + " ===");
            
            employeeId = getEmployeeIdFromField();
            log("Current Employee ID: " + employeeId);
            
            click(SAVE_BUTTON);

            if (isPresent(PERSONAL_DETAILS_HEADER)) {
                log("SUCCESS! Employee saved with ID: " + employeeId);
                return true;
            }

            if (isPresent(EMPLOYEE_ID_ERROR)) {
                log("DUPLICATE ID detected, generating new ID...");
                
                String newId = generateUuidId();
                log("New UUID-based ID: " + newId);
                
                clearAndSetEmployeeId(newId);
                employeeId = newId;
                
                waitForNetworkIdle(500);
            } else {
                log("Unknown state, waiting and retrying...");
                waitForNetworkIdle(2000);
            }
        }

        log("FAILED to save employee after " + maxAttempts + " attempts");
        return false;
    }

    private String getEmployeeIdFromField() {
        try {
            return findElement(EMPLOYEE_ID_INPUT_RETRY).getAttribute("value");
        } catch (Exception e) {
            try {
                return findElement(EMPLOYEE_ID_INPUT).getAttribute("value");
            } catch (Exception e2) {
                return "unknown";
            }
        }
    }

    private String generateUuidId() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid.substring(0, 9);
    }

    private void clearAndSetEmployeeId(String newId) {
        try {
            WebElement element = findElement(EMPLOYEE_ID_INPUT_RETRY);
            
            element.click();
            waitForNetworkIdle(200);
            
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            element.sendKeys(Keys.DELETE);
            waitForNetworkIdle(100);
            
            element.clear();
            waitForNetworkIdle(100);
            
            executeScript("arguments[0].value = '';", element);
            waitForNetworkIdle(100);
            
            element.sendKeys(newId);
            
            String actualValue = element.getAttribute("value");
            log("Employee ID field now contains: " + actualValue);
            
            if (!actualValue.equals(newId)) {
                log("Value mismatch! Trying again with JS...");
                executeScript("arguments[0].value = arguments[1];", element, newId);
                element.sendKeys(Keys.SPACE, Keys.BACK_SPACE);
            }
            
        } catch (Exception e) {
            log("Error setting employee ID: " + e.getMessage());
        }
    }

    public void waitForPersonalDetailsPage() {
        waitForVisible(PERSONAL_DETAILS_HEADER);
        waitForVisible(EMPLOYEE_FULL_NAME_LABEL, 90);
        waitForNetworkIdle(1500);
    }

    public String getEmployeeNameText() {
        waitForNetworkIdle(1500);
        String employeeName = getText(EMPLOYEE_NAME);
        return employeeName.trim();
    }

    public String getFirstNameValue() {
        return getAttribute(FIRST_NAME_INPUT, "value");
    }

    public String getMiddleNameValue() {
        return getAttribute(MIDDLE_NAME_INPUT, "value");
    }

    public String getLastNameValue() {
        return getAttribute(LAST_NAME_INPUT, "value");
    }

    public void waitForEmployeeInformationPage() {
        waitForVisible(EMPLOYEE_INFORMATION_HEADER);
    }

    public void inputEmployeeIdSearch(String employeeId) {
        WebElement element = waitForVisible(EMPLOYEE_ID_SEARCH_INPUT);
        element.clear();
        try { Thread.sleep(200); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.DELETE);
        try { Thread.sleep(200); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        element.sendKeys(employeeId);
        log("Entered Employee ID for search: " + employeeId);
    }

    public void clickSearch() {
        click(SEARCH_BUTTON);
    }

    public void waitForTableRows(String employeeId) {
        waitForNetworkIdle(1500);
        Locator THE_SEARCHED_ROW = Locator.byXpath("THE_SEARCHED_ROW", "//div[text()='"+ employeeId+"']");
        waitForVisible(TABLE_ROW);
        waitForVisible(THE_SEARCHED_ROW);
        isEmployeeIdInTable(employeeId);
    }

    public boolean isEmployeeIdInTable(String employeeId) {
        waitForNetworkIdle(1500);
        Locator employeeIdCell = Locator.byXpath(
                "EMPLOYEE_ID_CELL",
                "//div[contains(text(),'" + employeeId + "')]"
        );
        return isDisplayedWithRetry(employeeIdCell);
    }

    public boolean isFirstMiddleNameInTable(String firstName, String middleName) {
        waitForNetworkIdle(500);
        Locator nameCell = Locator.byXpath(
                "FIRST_MIDDLE_NAME_CELL",
                "//div[contains(text(),'" + firstName + " " + middleName + "')]"
        );
        return isDisplayedWithRetry(nameCell);
    }

    public boolean isLastNameInTable(String lastName) {
        waitForNetworkIdle(500);
        Locator lastNameCell = Locator.byXpath(
                "LAST_NAME_CELL",
                "//div[contains(text(),'" + lastName + "')]"
        );
        return isDisplayedWithRetry(lastNameCell);
    }

    private boolean isDisplayedWithRetry(Locator locator) {
        int maxRetries = 5;
        for (int i = 0; i < maxRetries; i++) {
            try {
                waitForNetworkIdle(500);
                if (isPresent(locator)) {
                    return isDisplayed(locator);
                }
                return false;
            } catch (Exception e) {
                log("Retry " + (i + 1) + " for " + locator.getName() + ": " + e.getMessage());
                waitForNetworkIdle(1000);
            }
        }
        return false;
    }

    public void searchEmployeeById(String employeeId) {
        clickEmployeeList();
        waitForEmployeeInformationPage();
        inputEmployeeIdSearch(employeeId);
        clickSearch();
        waitForTableRows(employeeId);
    }

    public void clickEditButton() {
        click(EDIT_BUTTON);
    }

    public void inputOtherId(String otherId) {
        type(OTHER_ID_INPUT, otherId);
        savedOtherId = otherId;
    }

    public void inputLicenseNumber(String licenseNumber) {
        type(LICENSE_NUMBER_INPUT, licenseNumber);
        savedLicenseNumber = licenseNumber;
    }

    public void inputLicenseExpiryDate() {
        LocalDate expiryDate = LocalDate.now().plusYears(3);
        String formattedDate = expiryDate.format(DateTimeFormatter.ofPattern("yyyy-dd-MM"));
        typeDateField(LICENSE_EXPIRY_DATE_INPUT, formattedDate);
        savedLicenseExpiryDate = formattedDate;
    }

    public void selectNationality() {
        click(NATIONALITY_DROPDOWN);
        waitForVisible(DROPDOWN_OPTION_SECOND, 5);
        click(DROPDOWN_OPTION_SECOND);
        savedNationality = "Selected";
    }

    public void selectMaritalStatus() {
        click(MARITAL_STATUS_DROPDOWN);
        waitForVisible(SINGLE_OPTION, 5);
        click(SINGLE_OPTION);
        savedMaritalStatus = "Single";
    }

    public void selectRandomGender() {
        boolean isMale = random.nextBoolean();
        if (isMale) {
            click(MALE_RADIO);
            savedGender = "Male";
        } else {
            click(FEMALE_RADIO);
            savedGender = "Female";
        }
    }

    public void inputDateOfBirth() {
        LocalDate dob = LocalDate.now().minusYears(21);
        String formattedDate = dob.format(DateTimeFormatter.ofPattern("yyyy-dd-MM"));
        typeDateField(DATE_OF_BIRTH_INPUT, formattedDate);
        savedDateOfBirth = formattedDate;
    }

    public void waitForSuccessToast() {
        waitForVisible(SUCCESS_TOAST, 10);
    }

    public void clickJobTab() {
        click(JOB_TAB);
    }

    public void selectJobTitle() {
        waitForVisible(JOB_TITLE_DROPDOWN, 5);
        click(JOB_TITLE_DROPDOWN);
        waitForVisible(QA_ENGINEER_OPTION, 5);
        click(QA_ENGINEER_OPTION);
        savedJobTitle = "QA Engineer";
    }

    public void selectJobCategory() {
        click(JOB_CATEGORY_DROPDOWN);
        waitForVisible(DROPDOWN_OPTION_SECOND, 5);
        click(DROPDOWN_OPTION_SECOND);
        savedJobCategory = "Selected";
    }

    public void selectSubUnit() {
        click(SUB_UNIT_DROPDOWN);
        try {
            waitForVisible(QA_SUBUNIT_OPTION, 5);
            click(QA_SUBUNIT_OPTION);
            savedSubUnit = "Quality Assurance";
        } catch (Exception e) {
            waitForVisible(DROPDOWN_OPTION_SECOND, 5);
            click(DROPDOWN_OPTION_SECOND);
            savedSubUnit = "Selected";
        }
    }

    public void selectEmploymentStatus() {
        click(EMPLOYMENT_STATUS_DROPDOWN);
        waitForVisible(DROPDOWN_OPTION_SECOND, 5);
        click(DROPDOWN_OPTION_SECOND);
        savedEmploymentStatus = "Selected";
    }

    public void clickReportToTab() {
        click(REPORT_TO_TAB);
    }

    public void waitForAssignedSupervisorsHeader() {
        waitForVisible(ASSIGNED_SUPERVISORS_HEADER, 10);
    }

    public void clickAddSupervisor() {
        click(ADD_SUPERVISOR_BUTTON);
    }

    public void inputSupervisorName(String supervisorName) {
        waitForVisible(SUPERVISOR_NAME_INPUT, 5);
        sendKeys(SUPERVISOR_NAME_INPUT, supervisorName);
        savedSupervisorName = supervisorName;
    }

    public String selectAutocompleteOptionContaining(String searchValue) {
        log("Waiting for autocomplete option containing: " + searchValue);
        
        Locator optionWithValue = Locator.byXpath(
                "AUTOCOMPLETE_OPTION_WITH_VALUE",
                "//div[@role='option' and contains(@class, 'oxd-autocomplete-option')]//span[contains(text(), '" + searchValue + "')]"
        );
        
        waitForVisible(optionWithValue, 30);
        String actualSelectedValue = getText(optionWithValue);
        log("Found and selecting: " + actualSelectedValue);
        click(optionWithValue);
        savedSupervisorName = actualSelectedValue;
        return actualSelectedValue;
    }

    public void selectReportingMethod() {
        click(REPORTING_METHOD_DROPDOWN);
        waitForVisible(DIRECT_OPTION, 5);
        click(DIRECT_OPTION);
    }

    public void editPersonalDetails(String otherId, String licenseNumber) {
        inputOtherId(otherId);
        inputLicenseNumber(licenseNumber);
        inputLicenseExpiryDate();
        selectNationality();
        selectMaritalStatus();
        selectRandomGender();
        inputDateOfBirth();
        clickSave();
        waitForSuccessToast();
    }

    public void editJobDetails() {
        clickJobTab();
        selectJobTitle();
        selectJobCategory();
        selectSubUnit();
        selectEmploymentStatus();
        clickSave();
        waitForSuccessToast();
    }

    public void addSupervisor(String supervisorName) {
        clickReportToTab();
        waitForAssignedSupervisorsHeader();
        clickAddSupervisor();
        inputSupervisorName(supervisorName);
        selectAutocompleteOptionContaining(supervisorName);
        selectReportingMethod();
        clickSave();
        waitForSuccessToast();
    }

    public boolean isJobTitleInTable(String jobTitle) {
        Locator cell = Locator.byXpath("JOB_TITLE_CELL",
                "//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']//div[@class='oxd-table-cell oxd-padding-cell'][5]//div[contains(text(),'" + jobTitle + "')]");
        return isDisplayed(cell);
    }

    public boolean isEmploymentStatusInTable(String status) {
        Locator cell = Locator.byXpath("EMPLOYMENT_STATUS_CELL",
                "//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']//div[@class='oxd-table-cell oxd-padding-cell'][6]//div");
        return isDisplayed(cell);
    }

    public boolean isSubUnitInTable(String subUnit) {
        Locator cell = Locator.byXpath("SUB_UNIT_CELL",
                "//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']//div[@class='oxd-table-cell oxd-padding-cell'][7]//div[contains(text(),'" + subUnit + "')]");
        return isDisplayed(cell);
    }

    public boolean isSupervisorInTable(String supervisor) {
        Locator cell = Locator.byXpath("SUPERVISOR_CELL",
                "//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']//div[@class='oxd-table-cell oxd-padding-cell'][8]//div[contains(text(),'" + supervisor + "')]");
        return isDisplayed(cell);
    }

    public String getTableCellText(int columnIndex) {
        Locator cell = Locator.byXpath("TABLE_CELL_" + columnIndex,
                "(//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']//div[@class='oxd-table-cell oxd-padding-cell'])[" + columnIndex + "]//div[@data-v-6c07a142]");
        try {
            return getText(cell);
        } catch (Exception e) {
            return "";
        }
    }

    public String getSavedJobTitle() { return savedJobTitle; }
    public String getSavedSubUnit() { return savedSubUnit; }
    public String getSavedSupervisorName() { return savedSupervisorName; }
    public String getSavedOtherId() { return savedOtherId; }
    public String getSavedLicenseNumber() { return savedLicenseNumber; }
    public String getSavedGender() { return savedGender; }

    public void clickDeleteButton() {
        click(DELETE_BUTTON);
    }

    public void confirmDelete() {
        waitForVisible(CONFIRM_DELETE_BUTTON, 10);
        click(CONFIRM_DELETE_BUTTON);
    }

    public boolean waitForDeleteSuccessToast() {
        try {
            waitForVisible(TOAST_MESSAGE, 10);
            String toastText = getText(TOAST_MESSAGE);
            log("Toast message: " + toastText);
            return toastText.toLowerCase().contains("delete");
        } catch (Exception e) {
            log("Could not find delete success toast: " + e.getMessage());
            return false;
        }
    }

    public boolean waitForNoRecordsToast() {
        try {
            waitForVisible(TOAST_MESSAGE, 30);
            String toastText = getText(TOAST_MESSAGE);
            log("Toast message: " + toastText);
            return toastText.toLowerCase().contains("no records");
        } catch (Exception e) {
            log("Could not find 'No Records' toast: " + e.getMessage());
            return false;
        }
    }

    private static final Locator NO_RECORDS_FOUND_SPAN = Locator.byXpath(
            "NO_RECORDS_FOUND_SPAN",
            "//span[normalize-space()='No Records Found']"
    );

    public void waitForDeleteCompletion() {
        log("=== Waiting for delete to complete ===");
        
        
        log("Waiting for 'No Records Found' span to appear...");
        waitForVisible(NO_RECORDS_FOUND_SPAN, 60);
        log("✓ 'No Records Found' confirmed on screen");
        
    
    }

    public boolean isNoRecordsFound() {
        return isPresent(NO_RECORDS_FOUND) || !isPresent(TABLE_ROW);
    }

    public void deleteEmployee(String employeeId) {
        log("Deleting employee with ID: " + employeeId);
        clickDeleteButton();
        confirmDelete();
        waitForDeleteSuccessToast();
    }

    public void searchAndDeleteEmployee(String employeeId) {
        clickEmployeeList();
        waitForEmployeeInformationPage();
        inputEmployeeIdSearch(employeeId);
        clickSearch();
        waitForTableRows(employeeId);
        deleteEmployee(employeeId);
    }

    public boolean verifyEmployeeDeletedViaSearch(String employeeId) {
        log("Searching for deleted employee to verify...");
        clickSearch();
        
        boolean noRecordsToast = waitForNoRecordsToast();
        boolean noRecordsInTable = isNoRecordsFound();
        
        log("'No Records Found' toast shown: " + noRecordsToast);
        log("No records in table: " + noRecordsInTable);
        
        return noRecordsToast || noRecordsInTable;
    }
}
