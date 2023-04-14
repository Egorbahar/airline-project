package com.godeltech.service;

import com.godeltech.persistence.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee findById(final Long id);

    Employee save(final Employee employee);

    List<Employee> findAll();

    Employee update(final Employee employee);

    void deleteById(final Long id);
}
