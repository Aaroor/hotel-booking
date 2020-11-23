package com.hotel.booking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.booking.model.VehicleBooking;

@Repository
public interface VehicleBookingRepository extends JpaRepository<VehicleBooking, Long> {

}
