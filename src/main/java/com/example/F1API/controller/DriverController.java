package com.example.F1API.controller;

import com.example.F1API.Request.CreateDriverRequest;
import com.example.F1API.Request.ResponseDriverRequest;
import com.example.F1API.model.Driver;
import com.example.F1API.service.DriverService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Validated
public class DriverController {

    @Autowired
    DriverService driverService;

    @GetMapping("/drivers")
    public List<ResponseDriverRequest> getDrivers() {

        List<ResponseDriverRequest> responseDriverReq = new ArrayList<>();
        List<Driver> drivers = driverService.findAll();
        for (Driver driver : drivers) {
            responseDriverReq.add(new ResponseDriverRequest(
                    driver.getName(),
                    driver.getAge(),
                    driver.getTeam().getId(),
                    driver.getTeam().getName()));
        }
        return responseDriverReq;
    }

    @GetMapping("/getDriversId")
    public Optional<Driver> getDriverId(Long driverId) {

        return driverService.findById(driverId);
    }

    @PostMapping(value = "drivers", consumes = "application/json")
    public ResponseDriverRequest createDriver(@RequestBody CreateDriverRequest driverReq) {
        Driver newDriver = Driver
                .builder()
                .name(driverReq.getName())
                .age(driverReq.getAge())
                .build();
        driverService.save(newDriver, driverReq.getTeamId());
        ResponseDriverRequest driverRequest = new ResponseDriverRequest();
        driverRequest.setName(newDriver.getName());
        driverRequest.setAge(newDriver.getAge());
        driverRequest.setTeamId(newDriver.getTeam().getId());
        driverRequest.setTeamName(newDriver.getTeam().getName());
        return driverRequest;
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
