package com.HimanshuBagga.CachingImplementation.Controllers;

import com.HimanshuBagga.CachingImplementation.dto.EmployeeDto;
import com.HimanshuBagga.CachingImplementation.services.EmployeeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public ResponseEntity<EmployeeDto> createNewEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto employeeDto1 = employeeService.createNewEmployee(employeeDto);
        return new ResponseEntity<>(employeeDto1 , HttpStatus.CREATED);
    }



}
