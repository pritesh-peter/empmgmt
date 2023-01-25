package com.management.employee.services.impl;

import com.management.employee.entities.Employee;
import com.management.employee.payloads.EmployeeDto;
import com.management.employee.repositories.EmployeeRepo;
import com.management.employee.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;



    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = this.modelMapper.map(employeeDto,Employee.class);
        Employee newEmployee = new Employee();
        employee.setIsActive(true);

        try{
            newEmployee = employeeRepo.save(employee);
        } catch (Exception e){
            System.out.println(e);
        }
        return this.modelMapper.map(newEmployee,EmployeeDto.class);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Integer empId) {
        Employee employee = this.employeeRepo.findById(empId).get();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setGender(employeeDto.getGender());
        Employee updatedEmployee = this.employeeRepo.save(employee);
        EmployeeDto employeeDto1 = this.modelMapper.map(updatedEmployee,EmployeeDto.class);
        return employeeDto1;
    }

    @Override
    public EmployeeDto getEmployeeById(Integer empId) {
        Employee employee = this.employeeRepo.findById(empId).get();

        return this.modelMapper.map(employee,EmployeeDto.class);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<Employee> employeeList = this.employeeRepo.findAll();

        List<EmployeeDto> employeeDtos = employeeList.stream().map(employee -> this.modelMapper.map(employee,EmployeeDto.class)).collect(Collectors.toList());
        return employeeDtos;
    }

    @Override
    public void deleteEmployee(Integer employeeId) {
        Employee employee = this.employeeRepo.findById(employeeId).get();
        this.employeeRepo.delete(employee);
    }
}
