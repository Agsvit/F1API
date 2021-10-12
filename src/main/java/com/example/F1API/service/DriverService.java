package com.example.F1API.service;

import com.example.F1API.model.Driver;
import com.example.F1API.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    @Autowired
    DriverRepository driverRepository;

    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    public Optional<Driver> findById(Long driverId) {
        return driverRepository.findById(driverId);
    }

    public <S extends Driver> S save(S newDriver) {
        return driverRepository.save(newDriver);
    }

    public void deleteById(Long id) {
        driverRepository.deleteById(id);
    }

}
