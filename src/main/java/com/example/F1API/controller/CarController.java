package com.example.F1API.controller;

import com.example.F1API.Request.CreateCarRequest;
import com.example.F1API.model.Car;
import com.example.F1API.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Validated
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping("/getCars")
    public List<Car> getCars() {
        return carService.findAll();
    }

    @GetMapping("/getCarsId")
    public Optional<Car> getCarId(Long carId) {
        return carService.findById(carId);
    }

    @PostMapping(value = "create-car", consumes = "application/json", produces = "application/json")
    public Car createCar(@RequestBody CreateCarRequest carReq) {
        Car newCar = Car
                .builder()
                .model(carReq.getModel())
                .color1(carReq.getColor1())
                .color2(carReq.getColor2())
                .build();
        return carService.save(newCar, carReq.getTeamId());

    }

    @PutMapping(value = "updateCar/{id}", consumes = "application/json", produces = "application/json")
    public Car updateCountry(Long id, @RequestBody CreateCarRequest carReq) {
        System.out.println(id);
        Optional<Car> carToUpdate = carService.findById(id);
        if (carToUpdate.isPresent()) {
            carToUpdate.get().setModel(carReq.getModel());
            carToUpdate.get().setColor1(carReq.getColor1());
            carToUpdate.get().setColor2(carReq.getColor2());
            carService.save(carToUpdate.get(), carReq.getTeamId());
            return carToUpdate.get();
        } else {
            ResponseEntity.badRequest().body("Car not found");
            return null;
        }
    }

    @DeleteMapping(value = "/deleteCar/{id}")
    public void deleteCar(Long id) {
        carService.deleteById(id);
    }

}
