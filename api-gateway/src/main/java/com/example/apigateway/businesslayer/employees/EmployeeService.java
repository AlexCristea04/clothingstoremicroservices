package com.example.apigateway.businesslayer.employees;

import com.example.apigateway.presentationlayer.employees.EmployeeRequestModel;
import com.example.apigateway.presentationlayer.employees.EmployeeResponseModel;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponseModel> getEmployees();
    EmployeeResponseModel getEmployeeByEmployeeId(String employeeId);
    EmployeeResponseModel addEmployee(EmployeeRequestModel employeeRequestModel);
    EmployeeResponseModel updateEmployee(EmployeeRequestModel updatedEmployee, String employeeId);
    void removeEmployee(String employeeId);
}
