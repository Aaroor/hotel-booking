package com.hotel.booking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.booking.model.Vehicle;
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
