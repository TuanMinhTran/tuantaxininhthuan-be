package com.example.viet_taxi.service.implement;

import com.example.viet_taxi.converter.BookingResponseDto;
import com.example.viet_taxi.dto.BookingRequestDto;
import com.example.viet_taxi.entity.BookingEntity;
import com.example.viet_taxi.repository.BookingRepository;
import com.example.viet_taxi.service.BookingService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public BookingResponseDto createBooking(BookingRequestDto request) {

        BookingEntity entity = BookingEntity.builder()
                .customerName(request.getName())
                .customerPhone(request.getPhone())
                .pickupLocation(request.getPickup())
                .dropoffLocation(request.getDropoff())
                .tripDate(LocalDate.parse(request.getDate()))
                .tripTime(LocalTime.parse(request.getTime()))
                .vehicleType(request.getVehicle())
                .tripType(request.getTripType())
                .note(request.getNote())
                .status("pending")
                .seen(false)
                .build();

        bookingRepository.save(entity);

        return mapToResponse(entity);
    }

    @Override
    public List<BookingResponseDto> getAllBookings() {

        return bookingRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public BookingResponseDto updateBooking(Long id, BookingRequestDto request) {

        BookingEntity entity = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        entity.setCustomerName(request.getName());
        entity.setCustomerPhone(request.getPhone());
        entity.setPickupLocation(request.getPickup());
        entity.setDropoffLocation(request.getDropoff());

        entity.setTripDate(LocalDate.parse(request.getDate()));
        entity.setTripTime(LocalTime.parse(request.getTime()));

        entity.setVehicleType(request.getVehicle());
        entity.setTripType(request.getTripType());

        entity.setNote(request.getNote());

        bookingRepository.save(entity);

        return mapToResponse(entity);
    }

    @Override
    public BookingResponseDto markSeen(Long id) {

        BookingEntity entity = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        entity.setSeen(true);

        bookingRepository.save(entity);

        return mapToResponse(entity);
    }

    @Override
    public BookingResponseDto updateStatus(Long id, String status) {

        BookingEntity entity = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        entity.setStatus(status);

        bookingRepository.save(entity);

        return mapToResponse(entity);
    }

    @Override
    public void markAllSeen() {

        List<BookingEntity> bookings = bookingRepository.findAll();

        for (BookingEntity booking : bookings) {

            booking.setSeen(true);
        }

        bookingRepository.saveAll(bookings);
    }

    @Override
    public void deleteBooking(Long id) {

        bookingRepository.deleteById(id);
    }

    @Override
    public BookingResponseDto cancelBooking(Long id) {

        BookingEntity entity = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        entity.setStatus("cancelled");

        bookingRepository.save(entity);

        return mapToResponse(entity);
    }

    private BookingResponseDto mapToResponse(BookingEntity entity) {

        return BookingResponseDto.builder()
                .id(entity.getId())
                .name(entity.getCustomerName())
                .phone(entity.getCustomerPhone())
                .pickup(entity.getPickupLocation())
                .dropoff(entity.getDropoffLocation())
                .date(
                        entity.getTripDate() != null
                                ? entity.getTripDate().toString()
                                : null
                )
                .time(
                        entity.getTripTime() != null
                                ? entity.getTripTime().toString()
                                : null
                )
                .vehicle(entity.getVehicleType())
                .tripType(entity.getTripType())
                .note(entity.getNote())
                .status(entity.getStatus())
                .seen(entity.getSeen())
                .createdAt(
                        entity.getCreatedAt() != null
                                ? entity.getCreatedAt().toString()
                                : null
                )
                .build();
    }
}
