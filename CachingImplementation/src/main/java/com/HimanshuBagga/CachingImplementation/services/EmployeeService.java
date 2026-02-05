package com.HimanshuBagga.CachingImplementation.services;

import com.HimanshuBagga.CachingImplementation.dto.EmployeeDto;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface EmployeeService {
    //getEmployeeById
    EmployeeDto getEmployeebyId(Long employeeId);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto createNewEmployee(EmployeeDto employeeDto);

    EmployeeDto updateEmployeeById(Long employeeId , EmployeeDto employeeDto);

    void deleteEmployeeById(Long employeeId);

    EmployeeDto updatePartialEmployee(Long employeeId , Map<String , Object> updates);
}
