package com.HimanshuBagga.CachingImplementation.services.impl;

import com.HimanshuBagga.CachingImplementation.dto.EmployeeDto;
import com.HimanshuBagga.CachingImplementation.entities.Employee;
import com.HimanshuBagga.CachingImplementation.exceptions.ResourceNotFoundException;
import com.HimanshuBagga.CachingImplementation.repositories.EmployeeRepository;
import com.HimanshuBagga.CachingImplementation.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.XSlf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@XSlf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Override
    public EmployeeDto getEmployeebyId(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> {
                    throw new ResourceNotFoundException("Employee not found with Id : "+ employeeId);
                }
        );
        return modelMapper.map(employee , EmployeeDto.class);
    }

    @Override
    public List<EmployeeDto> getAllEmployees(){
        List<EmployeeDto> employee = employeeRepository.findAll()
                .stream()
                .map(employee1 -> modelMapper.map(employee1 , EmployeeDto.class))
                .collect(Collectors.toList());

        return employee;
    }

    @Override
    public EmployeeDto createNewEmployee(EmployeeDto employeeDto){
        List<Employee> employee = employeeRepository.findByEmail(employeeDto.getEmail());
        if(!employee.isEmpty()){
            throw new RuntimeException("Employee Already Exists with email: " + employeeDto.getEmail());
        }
        Employee newEmployee = modelMapper.map(employeeDto , Employee.class);
        Employee savedEmployee = employeeRepository.save(newEmployee);
        return modelMapper.map(savedEmployee , EmployeeDto.class);
    }

    @Override
    public EmployeeDto updateEmployeeById(Long employeeId , EmployeeDto employeeDto){
        // check exists , if not create and save
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> {
                    throw new ResourceNotFoundException("Employee not found with Id: " + employeeId);
                }
        );
        if(!employee.getEmail().equals(employeeDto.getEmail())){
            throw new RuntimeException("The Employee with this Email Id is not found: " + employeeDto.getEmail());
        }
        employeeDto.setId(null);
        modelMapper.map(employeeDto , Employee.class);

        Employee updatedEmployee = employeeRepository.save(employee);
        return modelMapper.map(updatedEmployee , EmployeeDto.class);
    }


}
