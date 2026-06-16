package com.example.viet_taxi.repository;

import com.example.viet_taxi.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    List<BookingEntity> findAllByOrderByCreatedAtDesc();
}