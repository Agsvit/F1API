package com.example.F1API.service;

import com.example.F1API.Request.CreateDriverRequest;
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

    public Optional<Driver> findById(Long driverId) {
        return driverRepository.findById(driverId);
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

}
