package com.management.employee.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.management.employee.entities.Employee;
import com.management.employee.payloads.EmployeeDto;
import com.management.employee.repositories.EmployeeRepo;
import com.management.employee.services.EmployeeService;
import com.sun.istack.NotNull;
import org.json.JSONString;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.ResultActions;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {


    @Autowired
    private MockMvc mockMvc;


    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();
//    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);


    @MockBean
  private EmployeeService employeeService;



    private EmployeeDto employeeDto;

//    EmployeeDto emp1 = new EmployeeDto(1,"pritesh","singh","2023-01-23","pritesh@gmail.com","Male");
//    EmployeeDto emp2 = new EmployeeDto(1,"sonam","singh","2024-01-23","sonam@gmail.com","Female");

//    Employee emp3 = new Employee(1,"jenny","singh","female", null,"jenny@gmail.com",null,true);
//
//    Employee emp4 = new Employee(2,"marie","singh","female", null,"marie@gmail.com",null,true);


    @BeforeEach
    public void setUp() throws Exception{
       employeeDto = EmployeeDto.builder()
                    .empId(1)
                    .firstName("Pritesh")
                    .lastName("Singh")
                    .dob("2023-01-24")
                    .email("pritesh@gmail.com")
                    .gender("Male")
                    .build();
    }

//    @Test
//    public void getEmployees() throws Exception {
//        List<Employee> employeeList = new ArrayList<>(Arrays.asList(emp3,emp4));
//
//        Mockito.when(employeeRepo.findAll()).thenReturn(employeeList);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/api/v1/employee/")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(2)));
//
//    }

    @Test
    public void addEmployeeTest() throws Exception{
        EmployeeDto inputEmployee = EmployeeDto.builder()
                .empId(1)
                .firstName("Pritejj")
                .lastName("Singh")
                .dob("2023-01-24")
                .email("pritesh@gmail.com")
                .gender("Male")
                .build();

        Mockito.when(employeeService.createEmployee(inputEmployee))
                .thenReturn(employeeDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employee/")
                        .content(objectMapper.writeValueAsString(inputEmployee))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    public void getEmployeeById() throws Exception{
        Mockito.when(employeeService.getEmployeeById(1))
                .thenReturn(employeeDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employee/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName")
                        .value(employeeDto.getFirstName())

                );
    }

}