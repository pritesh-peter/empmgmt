package com.management.employee.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {

    private Integer empId;

    private String firstName;

    private String lastName;

    private String dob;

    private String email;

    private String gender;


}
