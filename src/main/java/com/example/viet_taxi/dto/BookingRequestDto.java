package com.example.viet_taxi.dto;

import lombok.Data;

@Data
public class BookingRequestDto {

    private String name;
    private String phone;

    private String pickup;
    private String dropoff;

    private String date;
    private String time;

    private String vehicle;
    private String tripType;

    private String note;
}
