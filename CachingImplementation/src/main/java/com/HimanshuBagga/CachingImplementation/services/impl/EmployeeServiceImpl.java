package com.HimanshuBagga.CachingImplementation.services.impl;

import com.HimanshuBagga.CachingImplementation.dto.EmployeeDto;
import com.HimanshuBagga.CachingImplementation.entities.Employee;
import com.HimanshuBagga.CachingImplementation.exceptions.ResourceNotFoundException;
import com.HimanshuBagga.CachingImplementation.repositories.EmployeeRepository;
import com.HimanshuBagga.CachingImplementation.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.apache.el.util.ReflectionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Override
    @Cacheable(cacheNames = "employees" , key = "#employeeId")
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
    @CachePut(cacheNames = "employees" , key = "#result.id") // get the result and update the result
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
    @CachePut(cacheNames = "employees" , key = "#employeeId")
    public EmployeeDto updateEmployeeById(Long employeeId , EmployeeDto employeeDto){
        // check exists , if not create and save
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee not found with Id: " + employeeId));
        modelMapper.map(employeeDto , employee);
        employee.setId(employeeId);

        Employee updatedEmployee = employeeRepository.save(employee);
        return modelMapper.map(updatedEmployee , EmployeeDto.class);
    }

    @Override
    public void deleteEmployeeById(Long employeeId){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> {
                    throw new ResourceNotFoundException("Employee not found with Id: "+ employeeId);
                }
        );
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public EmployeeDto updatePartialEmployee(Long employeeId , Map<String , Object> updates){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> {
                    throw new ResourceNotFoundException("Employee not found with Id: " + employeeId);
                }
        );
        updates.forEach((fieldName , value) -> {
            Field field = ReflectionUtils.findField(Employee.class, fieldName);
            if(field != null){
                field.setAccessible(true);
                ReflectionUtils.setField(field, employee, value);
            }
        });
        Employee updatedEmployee = employeeRepository.save(employee);
        return modelMapper.map(updatedEmployee , EmployeeDto.class);
    }
}
