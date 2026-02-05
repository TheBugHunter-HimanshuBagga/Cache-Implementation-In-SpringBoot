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


}
