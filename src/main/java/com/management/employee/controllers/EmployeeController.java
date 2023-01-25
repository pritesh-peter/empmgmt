package com.management.employee.controllers;

import com.management.employee.payloads.ApiResponse;
import com.management.employee.payloads.EmployeeDto;
import com.management.employee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //Create new Employee
    @PostMapping("/")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto createdEmployeeDto = this.employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(createdEmployeeDto, HttpStatus.CREATED);
    }

    //Get employee by id
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Integer employeeId){

        return ResponseEntity.ok(this.employeeService.getEmployeeById(employeeId));
    }

    //Get all employees
    @GetMapping("/")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        return ResponseEntity.ok(this.employeeService.getAllEmployee());
    }

    //Delete employee by Id
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable Integer employeeId){
        this.employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(new ApiResponse("Employee deleted successfully",true),HttpStatus.OK);
    }

    //Update Employee
    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable Integer employeeId){
        EmployeeDto updatedEmployee = this.employeeService.updateEmployee(employeeDto,employeeId);
        return ResponseEntity.ok(updatedEmployee);
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
        return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
