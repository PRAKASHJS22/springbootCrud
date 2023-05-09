package springbootreact.springbootreact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootreact.springbootreact.exception.ResourceNotFoundException;
import springbootreact.springbootreact.model.Employee;
import springbootreact.springbootreact.repository.EmployeeRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    //get all employees;
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    //create employee rest api
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    //get employee by id
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Employee not exit with id :" + id));
        return ResponseEntity.ok(employee);
    }

    // update employee rest api
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmploee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Employee not exit with id :" + id));
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());

        Employee updatedEMployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEMployee);
    }
        // delete employee
        @DeleteMapping("/employees/{id}")
        public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
            Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not exit with id :" + id));
            employeeRepository.delete(employee);
            Map<String,Boolean> response = new HashMap<>();
            response.put("deleted",Boolean.TRUE);
            return ResponseEntity.ok(response);
        }

}
