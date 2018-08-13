package mum.swe.CRMSSpringApp.service;

import java.util.List;
import java.util.Optional;

public interface IService<T> {
    List<T> findAll();

    T save(T object);

    Optional<T> findById(Long id);

    void delete(Long id);
//	 void updateCar(Car oldCar, Car newCar);
}
