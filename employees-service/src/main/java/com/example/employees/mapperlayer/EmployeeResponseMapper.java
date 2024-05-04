package com.example.employees.mapperlayer;

import com.example.employees.datalayer.Employee;
import com.example.employees.presentationlayer.EmployeeController;
import com.example.employees.presentationlayer.EmployeeResponseModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface EmployeeResponseMapper {

    @Mapping(expression = "java(employee.getEmployeeIdentifier().getEmployeeId())", target = "employeeId")
    @Mapping(expression = "java(employee.getLastName())", target = "lastName")
    @Mapping(expression = "java(employee.getFirstName())", target = "firstName")
    @Mapping(expression = "java(employee.getJobTitle())", target = "jobTitle")
    @Mapping(expression = "java(employee.getDepartment())", target = "department")
    @Mapping(expression = "java(employee.getSalary())", target = "salary")
    EmployeeResponseModel entityToResponseModel(Employee employee);

    List<EmployeeResponseModel> entityListToResponseModelList(List<Employee> employees);

    @AfterMapping
    default void addLinks(@MappingTarget EmployeeResponseModel model){

        Link selfLink = linkTo(methodOn(EmployeeController.class)
                .getEmployeeByEmployeeId(model.getEmployeeId()))
                .withSelfRel();
        model.add(selfLink);

        Link employeesLink =
                linkTo(methodOn(EmployeeController.class)
                        .getEmployees())
                        .withRel("All employees");
        model.add(employeesLink);
    }
}