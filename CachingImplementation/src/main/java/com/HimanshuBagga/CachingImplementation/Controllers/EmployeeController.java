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
import java.util.Map;
import java.util.Objects;

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

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
        List<EmployeeDto> employeeDtos = employeeService.getAllEmployees();
        return ResponseEntity.ok(employeeDtos);
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createNewEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto employeeDto1 = employeeService.createNewEmployee(employeeDto);
        return new ResponseEntity<>(employeeDto1 , HttpStatus.CREATED);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployeeById(@PathVariable Long employeeId,
                                                          @RequestBody EmployeeDto employeeDto
                                                          )
    {
        EmployeeDto employeeDto1 = employeeService.updateEmployeeById(employeeId , employeeDto);
        return ResponseEntity.ok(employeeDto);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> deleteEmployeeById(@PathVariable Long employeeId){
        employeeService.deleteEmployeeById(employeeId);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> updatePartialEmployee(@PathVariable Long employeeId , @RequestBody Map<String , Object> updates){
        EmployeeDto employeeDto = employeeService.updatePartialEmployee(employeeId , updates);
        return ResponseEntity.ok(employeeDto);
    }
}
