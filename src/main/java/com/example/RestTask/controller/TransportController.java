package com.example.RestTask.controller;

import com.example.RestTask.entities.TransportEntity;
import com.example.RestTask.services.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransportController {

    @Autowired
    TransportService transportService;

    @GetMapping("/transports")
    public ResponseEntity<List<TransportEntity>> retrieveTransportList() {
        return transportService.retrieveTransportList();
    }
}
