package com.example.viet_taxi.controller;

import com.example.viet_taxi.converter.BookingResponseDto;
import com.example.viet_taxi.dto.BookingRequestDto;
import com.example.viet_taxi.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
@CrossOrigin("*")

public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public BookingResponseDto create(
            @RequestBody BookingRequestDto request
    ) {
        return bookingService.createBooking(request);
    }

    @GetMapping
    public List<BookingResponseDto> getAll() {
        return bookingService.getAllBookings();
    }

    @PutMapping("/{id}")
    public BookingResponseDto update(
            @PathVariable Long id,
            @RequestBody BookingRequestDto request
    ) {
        return bookingService.updateBooking(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id
    ) {
        bookingService.deleteBooking(id);
    }

    @PatchMapping("/{id}/cancel")
    public BookingResponseDto cancel(
            @PathVariable Long id
    ) {
        return bookingService.cancelBooking(id);
    }

    @PatchMapping("/{id}/seen")
    public BookingResponseDto markSeen(
            @PathVariable Long id
    ) {
        return bookingService.markSeen(id);
    }

    @PatchMapping("/seen-all")
    public ResponseEntity<?> markAllSeen() {

        bookingService.markAllSeen();

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {

        return ResponseEntity.ok(
                bookingService.updateStatus(id, status)
        );
    }
}
