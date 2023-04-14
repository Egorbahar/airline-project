package com.godeltech.service.impl;

import com.godeltech.component.LocalMessageSource;
import com.godeltech.exception.ResourceNotFoundException;
import com.godeltech.persistence.model.Employee;
import com.godeltech.persistence.reposirtory.EmployeeRepository;
import com.godeltech.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final LocalMessageSource messageSource;


    @Override
    public Employee findById(Long id) {
        log.debug("Find category with id:{}", id);
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(messageSource.getMessage("error.record.notExist", new Object[]{})));
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        log.debug("Save employee");
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        log.debug("Find all employees");
        return employeeRepository.findAll();
    }

    @Override
    @Transactional
    public Employee update(Employee employee) {
        log.debug("Update employee with id:{}", employee.getId());
        findById(employee.getId());
        return employeeRepository.saveAndFlush(employee);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.debug("Delete employee with id:{}", id);
        findById(id);
        employeeRepository.deleteById(id);
    }
}
