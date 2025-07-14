package com.example.RestTask.entities;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TransportEntity {
    private int id;
    private String description;
    private String type;
    private String startTimeStamp;
    private String endTimeStamp;
}
