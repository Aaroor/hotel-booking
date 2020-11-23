package com.hotel.booking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.booking.model.Driver;
@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {

}
