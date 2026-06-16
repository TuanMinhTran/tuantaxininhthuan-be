package com.example.viet_taxi.converter;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingResponseDto {

    private Long id;

    private String name;
    private String phone;

    private String pickup;
    private String dropoff;

    private String date;
    private String time;

    private String vehicle;
    private String tripType;

    private String note;

    private String status;

    private Boolean seen;

    private String createdAt;
}
