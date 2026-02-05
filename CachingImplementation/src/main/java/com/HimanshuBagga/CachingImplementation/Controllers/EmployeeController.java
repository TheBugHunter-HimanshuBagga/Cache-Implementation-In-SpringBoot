package com.HimanshuBagga.CachingImplementation.Controllers;

import com.HimanshuBagga.CachingImplementation.dto.EmployeeDto;
import com.HimanshuBagga.CachingImplementation.services.EmployeeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Employee/v1")
@FieldDefaults(makeFinal = true , level = AccessLevel.PRIVATE)
public class EmployeeController {

    EmployeeService employeeService;


    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeebyId(@PathVariable Long employeeId){
        EmployeeDto employeeDto = employeeService.getEmployeebyId(employeeId);
        return ResponseEntity.ok(employeeDto);
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
        List<EmployeeDto> employeeDtos = employeeService.getAllEmployees();
        return ResponseEntity.ok(employeeDtos);
    }



}
