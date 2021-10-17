package com.example.F1API.controller;

import com.example.F1API.controller.request.create.CreateDriverRequest;
import com.example.F1API.controller.request.response.ResponseDriverRequest;
import com.example.F1API.model.Driver;
import com.example.F1API.service.DriverService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/drivers")
    public List<ResponseDriverRequest> getDrivers() {

        List<ResponseDriverRequest> responseDriverReq = new ArrayList<>();
        List<Driver> drivers = driverService.findAll();
        for (Driver driver : drivers) {
            responseDriverReq.add(new ResponseDriverRequest(
                    driver.getId(),
                    driver.getName(),
                    driver.getAge(),
                    driver.getTeam().getId(),
                    driver.getTeam().getName()));
        }
        return responseDriverReq;
    }

    @GetMapping("/drivers/{name}")
    public ResponseDriverRequest getDriverByName(String name) {
        Driver driver = driverService.findByName(name);
        ResponseDriverRequest driverRequest = new ResponseDriverRequest(
                driver.getId(),
                driver.getName(),
                driver.getAge(),
                driver.getTeam().getId(),
                driver.getTeam().getName());

        return driverRequest;
    }

    @PostMapping(value = "/drivers", consumes = "application/json")
    public ResponseDriverRequest createDriver(@RequestBody CreateDriverRequest driverReq) {
        Driver newDriver = Driver
                .builder()
                .name(driverReq.getName())
                .age(driverReq.getAge())
                .build();
        driverService.save(newDriver, driverReq.getTeamId());
        ResponseDriverRequest driverRequest = new ResponseDriverRequest();
        driverRequest.setId(newDriver.getId());
        driverRequest.setName(newDriver.getName());
        driverRequest.setAge(newDriver.getAge());
        driverRequest.setTeamId(newDriver.getTeam().getId());
        driverRequest.setTeamName(newDriver.getTeam().getName());
        return driverRequest;
    }

    @PutMapping(value = "/drivers/{id}")
    public ResponseDriverRequest updateDriver(@PathVariable(value = "id") Long id, @RequestBody CreateDriverRequest driverReq) {
        Driver driver = driverService.update(driverReq, id);
        ResponseDriverRequest driverRespReq = new ResponseDriverRequest(
                driver.getId(),
                driver.getName(),
                driver.getAge(),
                driver.getTeam().getId(),
                driver.getTeam().getName());
        return driverRespReq;
    }

    @DeleteMapping(value = "/drivers/{id}")
    public void deleteDriver(Long id) {
        driverService.deleteById(id);
    }
}
