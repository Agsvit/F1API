package com.example.F1API.service;

import com.example.F1API.exception.DriverNotFound;
import com.example.F1API.exception.TeamNotFound;
import com.example.F1API.model.Driver;
import com.example.F1API.model.Team;
import com.example.F1API.repository.DriverRepository;
import com.example.F1API.repository.TeamRepository;
import com.example.F1API.controller.request.create.CreateDriverRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    private final DriverRepository driverRepository;
    private final TeamRepository teamRepository;

    public DriverService(DriverRepository driverRepository, TeamRepository teamRepository) {
        this.driverRepository = driverRepository;
        this.teamRepository = teamRepository;
    }

    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    public Driver findById(Long driverId) {
        return driverRepository.findById(driverId).orElseThrow(DriverNotFound::new);
    }

    public Driver save(Driver newDriver, Long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow(TeamNotFound::new);
        newDriver.setTeam(team);
        driverRepository.save(newDriver);
        return newDriver;
    }


    public <S extends Driver> S save(S newDriver) {
        return driverRepository.save(newDriver);
    }

    public void deleteById(Long id) {
        driverRepository.deleteById(id);
    }

    public Driver update(CreateDriverRequest driverReq, Long id) {
        Driver driver = this.findById(id);
        driver.setName(driverReq.getName());
        driver.setAge(driverReq.getAge());
        return driverRepository.save(driver);
    }
}
