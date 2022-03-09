package com.example.controller;

import com.example.model.EndPointData;
import com.example.repository.EndpointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EndPointController {

    @Autowired
    EndpointRepository endpointRepository;

    @GetMapping("/getAll")
    public ResponseEntity<List<EndPointData>> getAll(@RequestParam(required = false) String title) {
        try {
            List<EndPointData> endpoints = new ArrayList<EndPointData>();
            if (title == null)
                endpointRepository.findAll().forEach(endpoints::add);
            else
                endpointRepository.findByTitleContaining(title).forEach(endpoints::add);
            if (endpoints.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(endpoints, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<EndPointData> createTutorial(@RequestBody EndPointData endPointData) {
        try {
            EndPointData endPointDt = endpointRepository
                    .save(new EndPointData(endPointData.getTitle(), endPointData.getDescription(), endPointData.isPublished()));
            return new ResponseEntity<>(endPointDt, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/published")
    public ResponseEntity<List<EndPointData>> findByPublished() {
        try {
            List<EndPointData> endpoints = endpointRepository.findByPublished(true);
            if (endpoints.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(endpoints, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/create/{id}")
    public ResponseEntity<EndPointData> updateTutorial(@PathVariable("id") long id, @RequestBody EndPointData endPointData) {
        Optional<EndPointData> endpoints = endpointRepository.findById(id);
        if (endpoints.isPresent()) {
            EndPointData endPointData1 = endpoints.get();
            endPointData1.setTitle(endPointData.getTitle());
            endPointData1.setDescription(endPointData.getDescription());
            endPointData1.setPublished(endPointData.isPublished());
            return new ResponseEntity<>(endpointRepository.save(endPointData1), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        try {
            endpointRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<HttpStatus> deleteAll() {
        try {
            endpointRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
