package com.example.RestTask.controller;

import com.example.RestTask.entities.TransportEntity;
import com.example.RestTask.services.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class TransportController {

    @Autowired
    TransportService transportService;

    @GetMapping("/transports")
    public ResponseEntity<List<TransportEntity>> retrieveTransportList() {
        ResponseEntity<List<TransportEntity>> response = transportService.retrieveTransportList();
        if(response.getBody() == null || response.getBody().isEmpty()) {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No transports found");
        }
        return response;
    }
}
