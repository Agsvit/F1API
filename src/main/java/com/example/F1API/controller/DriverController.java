package com.example.F1API.controller;

import com.example.F1API.model.Driver;
import com.example.F1API.service.DriverService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Validated
public class DriverController {

    @Autowired
    DriverService driverService;

    @GetMapping("/getDrivers")
    public List<Driver> getDrivers() {
        return driverService.findAll();
    }

    @GetMapping("/getDriversId")
    public Optional<Driver> getDriverId(Long driverId) {
        return driverService.findById(driverId);
    }

    @PostMapping(value = "createDriver", consumes = "application/json", produces = "application/json")
    public Driver createDriver(@RequestBody Driver driver) {
        Driver newDriver = Driver.builder().name(driver.getName()).age(driver.getAge()).build();
        driverService.save(newDriver);
        return newDriver;
    }

    @PutMapping(value = "updateDriver/{id}", consumes = "application/json", produces = "application/json")
    public Driver updateCountry(Long id, @RequestBody Driver driver) {
        System.out.println(id);
        Optional<Driver> driverToUpdate = driverService.findById(id);
        if (driverToUpdate.isPresent()) {
            driverToUpdate.get().setName(driver.getName());
            driverToUpdate.get().setAge(driver.getAge());
            driverService.save(driverToUpdate.get());
            return driverToUpdate.get();
        } else {
            ResponseEntity.badRequest().body("Driver not found");
            return null;
        }
    }

    @DeleteMapping(value = "/deleteDriver/{id}")
    public void deleteDriver(Long id) {
        driverService.deleteById(id);
    }
}
