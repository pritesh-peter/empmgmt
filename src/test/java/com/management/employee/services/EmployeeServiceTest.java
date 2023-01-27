package com.management.employee.services;

import com.management.employee.entities.Employee;
import com.management.employee.payloads.EmployeeDto;
import com.management.employee.repositories.EmployeeRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepo employeeRepo;


    @BeforeEach
    void setUp(){
        Employee employee = Employee.builder()
                .empId(1)
                .firstName("Pritesh")
                .lastName("Singh")
                .dob(null)
                .email("pritesh@gmail.com")
                .gender("Male")
                .build();

        EmployeeDto employeeDto = EmployeeDto.builder()
                .empId(1)
                .firstName("Pritesh")
                .lastName("Singh")
                .dob(null)
                .email("pritesh@gmail.com")
                .gender("Male")
                .build();

        EmployeeDto employeeDto1 = EmployeeDto.builder()
                .empId(2)
                .firstName("Sonam")
                .lastName("Singh")
                .dob(null)
                .email("sonam@gmail.com")
                .gender("Female")
                .build();

        List<EmployeeDto> employeeDtoList = new ArrayList<>(Arrays.asList(employeeDto,employeeDto1));

//        Mockito.when(employeeService.getEmployeeById(1)).thenReturn(employeeDto);
//
//        Mockito.when(employeeService.getEmployeeById(2)).thenReturn(employeeDto1);
//
//        Mockito.when(employeeService.getAllEmployee()).thenReturn(employeeDtoList);

        Mockito.when(employeeRepo.findById(1)).thenReturn(Optional.ofNullable(employee));
    }

    @Test
    public void whenValidEmpId_thenEmloyeeShouldFound() throws Exception{

        EmployeeDto employeeDto= employeeService.getEmployeeById(1);

        System.out.println("get the employee with id"+employeeDto);
        assertEquals("pritesh@gmail.com",employeeDto.getEmail());

    }

    @Test
    public void getallEmployeeListTest() throws Exception{
        List<EmployeeDto> employeeDtoList = this.employeeService.getAllEmployee();
        System.out.println("this is employee list checking"+employeeDtoList);
        assertEquals(2,employeeDtoList.size());
    }

}