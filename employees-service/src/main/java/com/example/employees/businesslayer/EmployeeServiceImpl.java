package com.example.employees.businesslayer;

import com.example.employees.datalayer.EmployeeIdentifier;
import com.example.employees.datalayer.Employee;
import com.example.employees.datalayer.EmployeeRepository;
import com.example.employees.mapperlayer.EmployeeRequestMapper;
import com.example.employees.mapperlayer.EmployeeResponseMapper;
import com.example.employees.presentationlayer.EmployeeRequestModel;
import com.example.employees.presentationlayer.EmployeeResponseModel;
import com.example.employees.utils.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeResponseMapper employeeResponseMapper;
    private final EmployeeRequestMapper employeeRequestMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeResponseMapper employeeResponseMapper, EmployeeRequestMapper employeeRequestMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeResponseMapper = employeeResponseMapper;
        this.employeeRequestMapper = employeeRequestMapper;
    }

    @Override
    public List<EmployeeResponseModel> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employeeResponseMapper.entityListToResponseModelList(employees);
    }

    @Override
    public EmployeeResponseModel getEmployeeByEmployeeId(String employeeId) {
        Employee employee = employeeRepository.findByEmployeeIdentifier_EmployeeId(employeeId);

        if (employee == null) {
            throw new NotFoundException("Unknown employeeId: " + employeeId);
        }
        return employeeResponseMapper.entityToResponseModel(employee);
    }

    @Override
    public EmployeeResponseModel addEmployee(EmployeeRequestModel employeeRequestModel) {
        Employee employee = employeeRequestMapper.requestModelToEntity(employeeRequestModel, new EmployeeIdentifier());

        return employeeResponseMapper.entityToResponseModel(employeeRepository.save(employee));
    }

    @Override
    public EmployeeResponseModel updateEmployee(EmployeeRequestModel employeeRequestModel, String employeeId) {
        Employee existingEmployee = employeeRepository.findByEmployeeIdentifier_EmployeeId(employeeId);

        if (existingEmployee == null) {
            throw new NotFoundException("Unknown employeeId: " + employeeId);
        }

        Employee updatedEmployee = employeeRequestMapper.requestModelToEntity(employeeRequestModel,
                existingEmployee.getEmployeeIdentifier());
        updatedEmployee.setId(existingEmployee.getId());

        Employee response = employeeRepository.save(updatedEmployee);
        return employeeResponseMapper.entityToResponseModel(response);
    }

    @Override
    public void removeEmployee(String employeeId) {
        Employee existingEmployee = employeeRepository.findByEmployeeIdentifier_EmployeeId(employeeId);

        if (existingEmployee == null) {
            throw new NotFoundException("Unknown employeeId: " + employeeId);
        }

        employeeRepository.delete(existingEmployee);
    }
}
