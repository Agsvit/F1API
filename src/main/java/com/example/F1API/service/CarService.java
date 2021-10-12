package com.example.F1API.service;

import com.example.F1API.model.Car;
import com.example.F1API.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Optional<Car> findById(Long carId) {
        return carRepository.findById(carId);
    }

    public <S extends Car> S save(S newCar) {
        return carRepository.save(newCar);
    }

    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

}
