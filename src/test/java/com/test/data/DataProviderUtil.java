package com.test.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataProviderUtil {

    private static final String TEST_DATA_PATH = "src/main/resources/Testdata.json";

    public static Object[][] getTestData(Method method) {
        String methodName = method.getName();
        List<TestData> allData = readJsonFile();
        List<TestData> filtered = new ArrayList<>();

        for (TestData data : allData) {
            if (data.getTestMethodName().equals(methodName) && data.isShouldRun()) {
                filtered.add(data);
            }
        }

        if (filtered.isEmpty()) {
            return new Object[0][0];
        }

        Object[][] result = new Object[filtered.size()][1];
        for (int i = 0; i < filtered.size(); i++) {
            result[i][0] = filtered.get(i);
        }

        return result;
    }

    public static List<TestData> readJsonFile() {
        try {
            Reader reader = new FileReader(TEST_DATA_PATH);
            Gson gson = new Gson();
            Type listType = new TypeToken<List<TestData>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (Exception e) {
            System.out.println("Error reading test data: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}

