package com.example.controller;

import com.example.exception.ErrorDetails;
import com.example.model.EndpointInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BaseController {
    @GetMapping(value = "/sample")
    public String SampleMsg() {
        return "hello";
    }

    @GetMapping(value = "/endPointsInfo")
    public ResponseEntity<Object> getList() {
        List<EndpointInfo> info = Arrays.asList(
                new EndpointInfo("Cloud Foundry sponsored by Pivotal", "2.34"),
                new EndpointInfo("Cloud Foundry sponsored by Pivotal2", "2.34")
        );
        return new ResponseEntity<Object>(info, HttpStatus.OK);
    }

    @GetMapping(path = "/endPointsInfo/{provider}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getListByProvider(@PathVariable("provider") String provider) {
        RestTemplate restTemplate = new RestTemplate();
        EndpointInfo endpointInfo;
        ErrorDetails errorDetails = new ErrorDetails();
        try {
            if (!provider.isEmpty() && ((provider.equalsIgnoreCase("PWC")) || provider.equalsIgnoreCase("BLU")))
                //return restTemplate.getForObject("https://api.run.pivotal.io/v2/info", EndpointInfo.class).toString();
                return new ResponseEntity<Object>(restTemplate.getForObject("https://api.run.pivotal.io/v2/info", EndpointInfo.class), HttpStatus.OK);
            else {
                errorDetails.setError("Invalid Provider");
                errorDetails.setDescription("Please give valid provider value");
                return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {
            errorDetails.setError("error");
            errorDetails.setDescription("something went wrong");
            return new ResponseEntity<Object>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //return restTemplate.exchange("https://api.run.pivotal.io/v2/info", HttpMethod.GET, null, String.class).getBody();
    }
}
