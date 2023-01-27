package com.management.employee.repositories;
import com.management.employee.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepoTest {

    @Autowired
    private EmployeeRepo employeeRepo;

    @BeforeEach
    void setUp(){
        Employee employee = Employee.builder()
                .empId(5)
                .firstName("Pritesh")
                .lastName("Singh")
                .dob(null)
                .email("pritesh@gmail.com")
                .gender("Male")
                .build();
    }

    @Test
    public void whenFindById_thenReturnEmployee(){
        Employee employee1 = employeeRepo.findById(4).get();
        assertEquals(employee1.getEmail(),"jenny@gmail.com");
    }

    @Test
    public void whenFindall_thenReturnAllEmployees(){
        List<Employee> employeeList = employeeRepo.findAll();
        assertEquals(employeeList.size(),4);
    }
}