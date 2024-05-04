package com.example.employees.businesslayer;

import com.example.employees.presentationlayer.EmployeeRequestModel;
import com.example.employees.presentationlayer.EmployeeResponseModel;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponseModel> getEmployees();
    EmployeeResponseModel getEmployeeByEmployeeId(String employeeId);
    EmployeeResponseModel addEmployee(EmployeeRequestModel employeeRequestModel);
    EmployeeResponseModel updateEmployee(EmployeeRequestModel updatedEmployee, String employeeId);
    void removeEmployee(String employeeId);
}
