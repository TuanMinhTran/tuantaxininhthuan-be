package com.example.viet_taxi.service;

import com.example.viet_taxi.converter.BookingResponseDto;
import com.example.viet_taxi.dto.BookingRequestDto;

import java.util.List;

public interface BookingService {

    BookingResponseDto createBooking(
            BookingRequestDto request
    );

    List<BookingResponseDto> getAllBookings();

    BookingResponseDto updateBooking(
            Long id,
            BookingRequestDto request
    );

    void deleteBooking(Long id);

    BookingResponseDto cancelBooking(Long id);

    BookingResponseDto markSeen(Long id);

    BookingResponseDto updateStatus(Long id, String status);

    void markAllSeen();
}
