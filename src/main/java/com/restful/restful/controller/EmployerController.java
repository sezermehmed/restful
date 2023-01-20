package com.restful.restful.controller;

import com.restful.restful.exception.ResourceNotFoundException;
import com.restful.restful.model.Employee;
import com.restful.restful.model.Employer;
import com.restful.restful.repository.EmployerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class EmployerController {
    @Autowired
    private EmployerRepository employerRepository;

    @GetMapping("/employers")
    public List<Employer> getAllEmployers() {
        return employerRepository.findAll();
    }
    @GetMapping("/employers/{id}")
    public ResponseEntity<Employer> getEmployerById(@Valid  @PathVariable(value = "id") Long employerId)
            throws ResourceNotFoundException {
        Employer employer = employerRepository.findById(employerId)
                .orElseThrow(() -> new ResourceNotFoundException("Employer not found for this id :: " + employerId));
        return ResponseEntity.ok().body(employer);
    }
    @PostMapping("/employers")
    public Employer createEmployer(@Valid @RequestBody Employer employer) {
        return employerRepository.save(employer);
    }
    @PutMapping("/employers/{id}")
    public ResponseEntity<Employer> updateEmployer(@PathVariable(value = "id") Long employerId,
                                                   @RequestBody Employer employerDetails) throws ResourceNotFoundException {
        Employer employer = employerRepository.findById(employerId)
                .orElseThrow(() -> new ResourceNotFoundException("Employer not found for this id :: " + employerId));

        employer.setFirstName(employerDetails.getFirstName());
        employer.setLastName(employerDetails.getLastName());
        employer.setOtherInfos(employerDetails.getOtherInfos());
        final Employer updatedEmployer = employerRepository.save(employer);
        return ResponseEntity.ok(updatedEmployer);
    }
    @DeleteMapping("/employers/{id}")
    public Map<String, Boolean> deleteEmployer(@PathVariable(value = "id") Long employerId)
            throws ResourceNotFoundException {
        Employer employer = employerRepository.findById(employerId)
                .orElseThrow(() -> new ResourceNotFoundException("Employer not found for this id :: " + employerId));

        employerRepository.delete(employer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
