package com.example.controller;

import com.example.exception.InvalidFieldException;
import com.example.model.EndPointData;
import com.example.repository.EndpointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SampleController {

    @Autowired
    EndpointRepository endpointRepository;

    @PostMapping("/createData")
    public String create(@RequestBody EndPointData endPointData) {
            if(StringUtils.isEmpty(endPointData.getTitle())){
                throw new InvalidFieldException("Title is required field");
            }
            EndPointData endPointDt = endpointRepository
                    .save(new EndPointData(endPointData.getTitle(), endPointData.getDescription(), false));
            return "Endpoint data saved successfully";
    }
    @ExceptionHandler
    public String invalidFieldException(InvalidFieldException exception) {
        return exception.getMessage();
    }
}
