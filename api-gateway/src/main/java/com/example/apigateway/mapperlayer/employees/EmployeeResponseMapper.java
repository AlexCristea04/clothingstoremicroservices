package com.example.apigateway.mapperlayer.employees;

import com.example.apigateway.presentationlayer.employees.EmployeeController;
import com.example.apigateway.presentationlayer.employees.EmployeeResponseModel;
import org.mapstruct.*;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface EmployeeResponseMapper {

    EmployeeResponseModel responseModelToResponseModel(EmployeeResponseModel employeeResponseModel);
    List<EmployeeResponseModel> responseModelListToResponseModelList(List<EmployeeResponseModel> listEmployeeResponseModel);

    @AfterMapping
    default void addLinks(@MappingTarget EmployeeResponseModel model) {
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
