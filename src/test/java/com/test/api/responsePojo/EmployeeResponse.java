package com.test.api.responsePojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeResponse {

    private List<EmployeeData> data;
    private Meta meta;
    private List<Object> rels;

    public EmployeeData getFirstEmployee() {
        if (data != null && !data.isEmpty()) {
            return data.get(0);
        }
        return null;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class EmployeeData {
        private Integer empNumber;
        private String lastName;
        private String firstName;
        private String middleName;
        private String employeeId;
        private String terminationId;
        private JobTitle jobTitle;
        private Subunit subunit;
        private EmpStatus empStatus;
        private List<Object> supervisors;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class JobTitle {
        private Integer id;
        private String title;
        private Boolean isDeleted;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Subunit {
        private Integer id;
        private String name;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class EmpStatus {
        private Integer id;
        private String name;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Meta {
        private int total;
    }
}
