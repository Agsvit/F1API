package com.example.F1API.service;

import com.example.F1API.exception.CarNotFound;
import com.example.F1API.exception.TeamNotFound;
import com.example.F1API.controller.request.create.CreateCarRequest;
import com.example.F1API.model.Car;
import com.example.F1API.model.Team;
import com.example.F1API.repository.CarRepository;
import com.example.F1API.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final TeamRepository teamRepository;

    public CarService(CarRepository carRepository, TeamRepository teamRepository) {
        this.carRepository = carRepository;
        this.teamRepository = teamRepository;
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Car findById(Long carId) {
        return carRepository.findById(carId).orElseThrow(CarNotFound::new);
    }

    public Car save(Car newCar, Long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow(TeamNotFound::new);
        newCar.setTeam(team);
        return carRepository.save(newCar);
    }

    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    public Car update(CreateCarRequest carReq, Long id) {
        Car car = this.findById(id);
        car.setModel(carReq.getModel());
        car.setColor1(carReq.getColor1());
        car.setColor2(carReq.getColor2());
        return carRepository.save(car);
    }
}
