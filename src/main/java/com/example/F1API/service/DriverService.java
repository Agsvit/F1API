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

    //Looks for a driver by id and returns a Driver object or throws exceptions (instead of returning an Optional)
    public Driver findById(Long driverId) {
        return driverRepository.findById(driverId).orElseThrow(DriverNotFound::new);
    }

    //Saves a new Driver and associates him to a team
    public Driver save(Driver newDriver, Long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow(TeamNotFound::new);
        newDriver.setTeam(team);
        driverRepository.save(newDriver);
        return newDriver;
    }

    public void deleteById(Long id) {
        driverRepository.deleteById(id);
    }

    //Update driver attributes and team
    public Driver update(CreateDriverRequest driverReq, Long id) {
        Driver driver = this.findById(id);
        Team team = teamRepository.findById(driverReq.getTeamId()).orElseThrow(TeamNotFound::new);
        driver.setName(driverReq.getName());
        driver.setAge(driverReq.getAge());
        driver.setTeam(team);
        return driverRepository.save(driver);
    }

    public Driver findByName(String name) {
        return driverRepository.findByName(name).orElseThrow(DriverNotFound::new);
    }
}
