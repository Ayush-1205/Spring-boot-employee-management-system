package net.javaguides.springbootbackend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class controller {
    @Autowired
    private repository employeeRepository;

    // get all employees
    @GetMapping("/employees")
    public List<model> getAllEemployees() {
        return employeeRepository.findAll();
    }

    // create employee rest api
    @PostMapping("/employees")
    public model createEmployee(@RequestBody model employee) {
        return employeeRepository.save(employee);
    }

    // get employee by id rest api
    @GetMapping("/employees/{id}")
    public ResponseEntity<model> getEmployeeById(@PathVariable Long id) {
        model employee = employeeRepository.findById(id)
                .orElseThrow(() -> new exception("Employee not exist with id:" + id));
        return ResponseEntity.ok(employee);
    }

    // update employee rest api
    @PutMapping("/employees/{id}")
    public ResponseEntity<model> updateEmployee(@PathVariable Long id, @RequestBody model employeedetails) {
        model employee = employeeRepository.findById(id)
                .orElseThrow(() -> new exception("Employee not exist with id:" + id));
        employee.setFirstName(employeedetails.getFirstName());
        employee.setLastName(employeedetails.getLastName());
        employee.setEmailId(employeedetails.getEmailId());
        model updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    // delete employee rest api
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
        model employee = employeeRepository.findById(id)
                .orElseThrow(() -> new exception("Employee not exist with id:" + id));
        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
