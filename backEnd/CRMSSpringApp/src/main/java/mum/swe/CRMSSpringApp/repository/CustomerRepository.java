package mum.swe.CRMSSpringApp.repository;

import mum.swe.CRMSSpringApp.model.Car;
import mum.swe.CRMSSpringApp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Serializable> {
//	void updateCar(Car oldCar, Car newCar);
}
