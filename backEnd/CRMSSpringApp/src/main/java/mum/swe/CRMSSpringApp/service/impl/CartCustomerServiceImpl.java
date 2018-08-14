package mum.swe.CRMSSpringApp.service.impl;

import mum.swe.CRMSSpringApp.model.Car;
import mum.swe.CRMSSpringApp.repository.CarRepository;
import mum.swe.CRMSSpringApp.service.CarService;
import mum.swe.CRMSSpringApp.service.CartCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cart")
public class CartCustomerServiceImpl implements CarService,CartCustomerService {

    @Autowired
    CarRepository carRepository;

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Car save(Car Car) {
        return carRepository.save(Car);
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        carRepository.deleteById(id);
    }

}

