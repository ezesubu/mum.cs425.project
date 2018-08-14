package mum.swe.CRMSSpringApp.repository;

import mum.swe.CRMSSpringApp.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}	
