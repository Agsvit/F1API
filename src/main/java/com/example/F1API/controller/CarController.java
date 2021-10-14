package com.example.F1API.controller;

import com.example.F1API.Request.CreateCarRequest;
import com.example.F1API.Request.ResponseCarRequest;
import com.example.F1API.Request.ResponseDriverRequest;
import com.example.F1API.model.Car;
import com.example.F1API.model.Driver;
import com.example.F1API.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Validated
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping("/cars")
    public List<ResponseCarRequest> getCars() {

        List<ResponseCarRequest> responseCarReq = new ArrayList<>();
        List<Car> cars = carService.findAll();
        for (Car car : cars) {
            responseCarReq.add(new ResponseCarRequest(
                    car.getId(),
                    car.getModel(),
                    car.getColor1(),
                    car.getColor2(),
                    car.getTeam().getId(),
                    car.getTeam().getName()));
        }
        return responseCarReq;
    }

    @GetMapping("/cars/{id}")
    public ResponseCarRequest getCarId(Long carId) {
        Car car = carService.findById(carId);
        ResponseCarRequest carRequest = new ResponseCarRequest(
                car.getId(),
                car.getModel(),
                car.getColor1(),
                car.getColor2(),
                car.getTeam().getId(),
                car.getTeam().getName());
        return carRequest;

    }

    @PostMapping(value = "create/car", consumes = "application/json", produces = "application/json")
    public ResponseCarRequest createCar(@RequestBody @Valid CreateCarRequest carReq) {
        Car newCar = Car
                .builder()
                .model(carReq.getModel())
                .color1(carReq.getColor1())
                .color2(carReq.getColor2())
                .build();
        carService.save(newCar, carReq.getTeamId());
        ResponseCarRequest carRequest = new ResponseCarRequest();
        carRequest.setModel(newCar.getModel());
        carRequest.setColor1(newCar.getColor1());
        carRequest.setColor2(newCar.getColor2());
        carRequest.setTeamId(newCar.getTeam().getId());
        carRequest.setTeamName(newCar.getTeam().getName());
        return carRequest;
    }


    @PutMapping(value = "customers/{id}")
    public ResponseCarRequest updateCar(@PathVariable(value = "id") Long id, @RequestBody CreateCarRequest carReq) {
        Car car = carService.update(carReq, id);
        ResponseCarRequest carRespReq = new ResponseCarRequest(
                car.getId(),
                car.getModel(),
                car.getColor1(),
                car.getColor2(),
                car.getTeam().getId(),
                car.getTeam().getName());
        return carRespReq;
    }

    @DeleteMapping(value = "/deleteCar/{id}")
    public void deleteCar(Long id) {
        carService.deleteById(id);
    }

}
