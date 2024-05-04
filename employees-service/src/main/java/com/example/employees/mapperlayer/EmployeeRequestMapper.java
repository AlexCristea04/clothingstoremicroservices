package com.example.employees.mapperlayer;

import com.example.employees.datalayer.EmployeeIdentifier;
import com.example.employees.datalayer.Employee;
import com.example.employees.presentationlayer.EmployeeRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeRequestMapper {
    @Mapping(target = "id", ignore = true)
    Employee requestModelToEntity(EmployeeRequestModel employeeRequestModel,
                                  EmployeeIdentifier employeeIdentifier);

}