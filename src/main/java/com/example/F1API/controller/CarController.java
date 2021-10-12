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

    @PostMapping(value = "createCar", consumes = "application/json", produces = "application/json")
    public Car createCar(@RequestBody CreateCarRequest carReq) {
        Car newCar =
                Car.builder().model(carReq.getModel()).color1(carReq.getColor1())
                .color2(carReq.getColor2()).build();
        carService.save(newCar);
        return newCar;
    }

    @PutMapping(value = "updateCar/{id}", consumes = "application/json", produces = "application/json")
    public Car updateCountry(Long id, @RequestBody Car car) {
        System.out.println(id);
        Optional<Car> carToUpdate = carService.findById(id);
        if (carToUpdate.isPresent()) {
            carToUpdate.get().setModel(car.getModel());
            carToUpdate.get().setColor1(car.getColor1());
            carToUpdate.get().setColor2(car.getColor2());
            carService.save(carToUpdate.get());
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
