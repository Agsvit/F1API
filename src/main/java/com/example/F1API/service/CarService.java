package com.example.F1API.service;

import com.example.F1API.request.create.CreateCarRequest;
import com.example.F1API.model.Car;
import com.example.F1API.model.Team;
import com.example.F1API.repository.CarRepository;
import com.example.F1API.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Car> car = carRepository.findById(carId);
        if (car.isPresent()) {
            return car.get();
        }
        return null;
    }

    public Car save(Car newCar, Long teamId) {
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isPresent()) {
            newCar.setTeam(team.get());
        return carRepository.save(newCar);
    }
        return null;
    }

    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    public Car update(CreateCarRequest carReq, Long id) {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isPresent()) {
            carOptional.get().setModel(carReq.getModel());
            carOptional.get().setColor1(carReq.getColor1());
            carOptional.get().setColor2(carReq.getColor2());

            return carRepository.save(carOptional.get());
        }
        //Exception
        return null;
    }
}
