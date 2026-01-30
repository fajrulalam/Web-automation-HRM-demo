package com.test.data;

import java.util.Map;

public class TestData {

    private String testMethodName;
    private String scenario;
    private boolean shouldRun;
    private Map<String, Object> testData;

    public String getTestMethodName() {
        return testMethodName;
    }

    public void setTestMethodName(String testMethodName) {
        this.testMethodName = testMethodName;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public boolean isShouldRun() {
        return shouldRun;
    }

    public void setShouldRun(boolean shouldRun) {
        this.shouldRun = shouldRun;
    }

    public Map<String, Object> getTestData() {
        return testData;
    }

    public void setTestData(Map<String, Object> testData) {
        this.testData = testData;
    }

    public String get(String key) {
        if (testData == null || !testData.containsKey(key)) {
            return null;
        }
        return String.valueOf(testData.get(key));
    }

    @Override
    public String toString() {
        return scenario;
    }
}

