package com.management.employee.repositories;

import com.management.employee.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

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
                .empId(1)
                .firstName("Pritesh")
                .lastName("Singh")
                .dob(null)
                .email("pritesh@gmail.com")
                .gender("Male")
                .build();
    }

    @Test
    public void whenFindById_thenReturnEmployee(){
        Employee employee1 = employeeRepo.findById(1).get();
        System.out.println("output heram hai  "+employee1);
        assertEquals (employee1.getEmail(),"pritesh@gmail.com");
    }

}