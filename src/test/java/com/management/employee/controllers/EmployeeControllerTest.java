package com.management.employee.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.management.employee.payloads.EmployeeDto;
import com.management.employee.repositories.EmployeeRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
//    ObjectWriter objectWriter = new ObjectWriter();

    @Mock
    private EmployeeRepo employeeRepo;

    @InjectMocks
    private EmployeeController employeeController;

    EmployeeDto emp1 = new EmployeeDto(1,"pritesh","singh","2023-01-23","pritesh@gmail.com","Male");
    EmployeeDto emp2 = new EmployeeDto(1,"sonam","singh","2024-01-23","sonam@gmail.com","Female");

    @Test
    void getAllEmployees() {
    }
}