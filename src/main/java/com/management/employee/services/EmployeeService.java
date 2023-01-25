package com.management.employee.services;

import com.management.employee.payloads.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto updateEmployee(EmployeeDto employee, Integer empId);

    EmployeeDto getEmployeeById(Integer empId);

    List<EmployeeDto> getAllEmployee();

    void deleteEmployee(Integer emplpyeeId);
}
