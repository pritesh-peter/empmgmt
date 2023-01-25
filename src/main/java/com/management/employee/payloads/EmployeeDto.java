package com.management.employee.payloads;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeDto {

    private Integer empId;

    private String firstName;

    private String lastName;

    private Date dob;

    private String email;

    private String gender;


}
