package com.example.apigateway.businesslayer.employees;

import com.example.apigateway.domainclientlayer.employees.EmployeeServiceClient;
import com.example.apigateway.mapperlayer.employees.EmployeeResponseMapper;
import com.example.apigateway.presentationlayer.employees.EmployeeRequestModel;
import com.example.apigateway.presentationlayer.employees.EmployeeResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeServiceClient employeeServiceClient;
    private final EmployeeResponseMapper employeeResponseMapper;

    public EmployeeServiceImpl(EmployeeServiceClient employeeServiceClient, EmployeeResponseMapper employeeResponseMapper) {
        this.employeeServiceClient = employeeServiceClient;
        this.employeeResponseMapper = employeeResponseMapper;
    }

    @Override
    public List<EmployeeResponseModel> getEmployees() {
        return employeeResponseMapper.responseModelListToResponseModelList(employeeServiceClient.getAllEmployees());
    }

    @Override
    public EmployeeResponseModel getEmployeeByEmployeeId(String employeeId) {
        return employeeResponseMapper.responseModelToResponseModel(employeeServiceClient.getEmployeeByEmployeeId(employeeId));
    }

    @Override
    public EmployeeResponseModel addEmployee(EmployeeRequestModel employeeRequestModel) {
        return employeeResponseMapper.responseModelToResponseModel(employeeServiceClient.createEmployee(employeeRequestModel));
    }

    @Override
    public EmployeeResponseModel updateEmployee(EmployeeRequestModel employeeRequestModel, String employeeId) {
        return employeeResponseMapper.responseModelToResponseModel(employeeServiceClient.updateEmployeeByEmployee_Id(employeeRequestModel, employeeId));
    }

    @Override
    public void removeEmployee(String employeeId) {
        employeeServiceClient.deleteEmployeeByEmployee_Id(employeeId);
    }
}
