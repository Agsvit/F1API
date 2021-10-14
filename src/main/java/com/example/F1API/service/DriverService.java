package com.example.F1API.service;

import com.example.F1API.request.create.CreateDriverRequest;
import com.example.F1API.model.Driver;
import com.example.F1API.model.Team;
import com.example.F1API.repository.DriverRepository;
import com.example.F1API.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Driver> driver = driverRepository.findById(driverId);
        if (driver.isPresent()) {
            return driver.get();
        }
        return null;
    }

    public Driver save(Driver newDriver, Long teamId) {
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isPresent()) {
            newDriver.setTeam(team.get());
            driverRepository.save(newDriver);
            return newDriver;
        }
        return null;
    }

    public <S extends Driver> S save(S newDriver) {
        return driverRepository.save(newDriver);
    }

    public void deleteById(Long id) {
        driverRepository.deleteById(id);
    }

    public Driver update(CreateDriverRequest driverReq, Long id) {
        Optional<Driver> driverOptional = driverRepository.findById(id);
        if (driverOptional.isPresent()) {
            driverOptional.get().setName(driverReq.getName());
            driverOptional.get().setAge(driverReq.getAge());

            return driverRepository.save(driverOptional.get());
        }
        //Exception
        return null;
    }
}
