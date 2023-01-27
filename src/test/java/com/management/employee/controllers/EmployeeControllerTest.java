package com.management.employee.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.management.employee.payloads.EmployeeDto;
import com.management.employee.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;


@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @MockBean
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() throws Exception{

        EmployeeDto emp1 = new EmployeeDto(4,"pritesh","singh","2023-01-23","pritesh@gmail.com","Male");
        EmployeeDto emp2 = new EmployeeDto(3,"sonam","singh","2024-01-23","sonam@gmail.com","Female");
        EmployeeDto emp3 = new EmployeeDto(2,"jenny","singh","2024-01-23","sonam@gmail.com","Female");

        List<EmployeeDto> employeeList = new ArrayList<>(Arrays.asList(emp1,emp2,emp3));

        Mockito.when(employeeService.getAllEmployee()).thenReturn((employeeList));

        Mockito.when(employeeService.getEmployeeById(4)).thenReturn(emp1);
    }

    @Test
    public void getEmployeesTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/employee/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(3)));

    }

    @Test
    public void addEmployeeTest() throws Exception{
        EmployeeDto inputEmployee = EmployeeDto.builder()
                .empId(1)
                .firstName("nnnnn")
                .lastName("Singh")
                .dob("2023-01-24")
                .email("pritesh@gmail.com")
                .gender("Male")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employee/")
                .content(objectMapper.writeValueAsString(inputEmployee))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    public void getEmployeeByIdTest() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employee/4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName")
                .value("pritesh"));
    }

}