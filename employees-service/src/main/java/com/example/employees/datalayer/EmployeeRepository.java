package com.example.employees.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByEmployeeIdentifier_EmployeeId (String employeeId);

    Boolean existsEmployeeByEmployeeIdentifier_EmployeeId (String employeeId);
}
