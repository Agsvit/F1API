package com.example.F1API.service;

import com.example.F1API.model.Car;
import com.example.F1API.model.Team;
import com.example.F1API.repository.CarRepository;
import com.example.F1API.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    //    @Autowired
    final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Autowired
    TeamRepository teamRepository;

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Optional<Car> findById(Long carId) {
        return carRepository.findById(carId);
    }

    public void save(Car newCar, Long teamId) {
//    public <S extends Car> S save(S newCar, Long teamId) {
        Team team = teamRepository.getById(teamId);
        newCar.setTeam(team);
        carRepository.save(newCar);
    }

    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

}
