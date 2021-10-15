package com.example.F1API.service;

import com.example.F1API.controller.request.create.CreateResultRequest;
import com.example.F1API.exception.DriverNotFound;
import com.example.F1API.exception.RaceNotFound;
import com.example.F1API.model.Driver;
import com.example.F1API.model.Race;
import com.example.F1API.model.Result;
import com.example.F1API.repository.DriverRepository;
import com.example.F1API.repository.RaceRepository;
import com.example.F1API.repository.ResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultService {

    private final ResultRepository resultRepository;
    private final RaceRepository raceRepository;
    private final DriverRepository driverRepository;

    public ResultService(ResultRepository resultRepository, RaceRepository raceRepository, DriverRepository driverRepository) {
        this.resultRepository = resultRepository;
        this.raceRepository = raceRepository;
        this.driverRepository = driverRepository;
    }

    public Race save(List<Result> newResults, Long raceId, List<Long> driversIds) {
        Race race = raceRepository.findById(raceId).orElseThrow(RaceNotFound::new);
        for (var i = 0; newResults.size() > i; i++) {
            Driver driver = driverRepository.findById(driversIds.get(i)).orElseThrow(DriverNotFound::new);
            newResults.get(i).setRace(race);
            newResults.get(i).setDriver(driver);
            resultRepository.save(newResults.get(i));
        }
        return race;
    }

    public Result findById(Long resultId) {
        return resultRepository.findById(resultId).orElseThrow();
    }

    public void deleteById(Long id) {
        resultRepository.deleteById(id);
    }

    public Result update(CreateResultRequest resultReq, Long id) {
        Result result = this.findById(id);
        Driver driver = driverRepository.findById(resultReq.getDriverId()).orElseThrow(DriverNotFound::new);
        result.setDriver(driver);
        result.setPosition(resultReq.getPosition());
    return resultRepository.save(result);
    }
}
