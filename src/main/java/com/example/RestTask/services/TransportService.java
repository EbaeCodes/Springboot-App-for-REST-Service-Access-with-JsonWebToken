package com.example.RestTask.services;

import com.example.RestTask.entities.TransportEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransportService {
    ResponseEntity<List<TransportEntity>> retrieveTransportList();
}
