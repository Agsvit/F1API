package com.example.F1API.controller;

import com.example.F1API.controller.request.create.CreateCarRequest;
import com.example.F1API.controller.request.response.ResponseCarRequest;
import com.example.F1API.model.Car;
import com.example.F1API.service.CarService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class CarController {

   private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

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

    @PostMapping(value = "/cars", consumes = "application/json", produces = "application/json")
    public ResponseCarRequest createCar(@RequestBody @Valid CreateCarRequest carReq) {
        Car newCar = Car
                .builder()
                .model(carReq.getModel())
                .color1(carReq.getColor1())
                .color2(carReq.getColor2())
                .build();
        carService.save(newCar, carReq.getTeamId());
        ResponseCarRequest carRequest = new ResponseCarRequest();
        carRequest.setId(newCar.getId());
        carRequest.setModel(newCar.getModel());
        carRequest.setColor1(newCar.getColor1());
        carRequest.setColor2(newCar.getColor2());
        carRequest.setTeamId(newCar.getTeam().getId());
        carRequest.setTeamName(newCar.getTeam().getName());
        return carRequest;
    }


    @PutMapping(value = "/cars/{id}")
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

    @DeleteMapping(value = "/cars/{id}")
    public void deleteCar(Long id) {
        carService.deleteById(id);
    }

}
