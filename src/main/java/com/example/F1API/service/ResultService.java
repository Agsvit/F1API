package com.example.F1API.service;

import com.example.F1API.model.Driver;
import com.example.F1API.model.Race;
import com.example.F1API.model.Result;
import com.example.F1API.repository.DriverRepository;
import com.example.F1API.repository.RaceRepository;
import com.example.F1API.repository.ResultRepository;
import com.example.F1API.request.create.CreateResultRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Result> save(List<Result> newResults, Long raceId, List<Long> driversIds) {
        Optional<Race> raceOptional = raceRepository.findById(raceId);
        if (raceOptional.isPresent()) {
            for (var i=0; newResults.size()>i; i++) {
                Optional<Driver> driverOptional = driverRepository.findById(driversIds.get(i));
                if (driverOptional.isPresent()) {
                    newResults.get(i).setRace(raceOptional.get());
                    newResults.get(i).setDriver(driverOptional.get());
                    resultRepository.save(newResults.get(i));
                }
                else newResults.remove(i);
            }
            return newResults;
        }
        return null;
    }


    public List<Result> findResultByRaceId(Long raceId) {
        Optional<Race> raceOptional = raceRepository.findById(raceId);
        if (raceOptional.isPresent()) {
            return raceOptional.get().getResults();
        }
        //exception
        return null;
    }
}
