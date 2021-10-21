package com.example.F1API.service;

import com.example.F1API.controller.request.create.CreateResultRequest;
import com.example.F1API.exception.DriverNotFound;
import com.example.F1API.exception.DuplicatedResult;
import com.example.F1API.exception.RaceNotFound;
import com.example.F1API.exception.ResultNotFound;
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

    //Saves a race with a list of results associated to the race and multiple drivers
    public Race save(List<Result> newResults, String raceTrack, List<String> driversNames) {
        Race raceA = raceRepository.findByTrack(raceTrack).orElseThrow(RaceNotFound::new);
        List<Result> results = raceA.getResults();
        //Goes through the new results to save them
        for (var i = 0; newResults.size() > i; i++) {
            Driver driver = driverRepository.findByNameContaining(driversNames.get(i)).orElseThrow(DriverNotFound::new);
            //Goes through the results of the specified race to compare them with each new results and ensure there is
            //no duplicated results
            //This verification works although it is creating an error in which the new results inserted do not
            // appear on the response... working on that
            for (Result result: results) {
                if (result.getPosition() == newResults.get(i).getPosition()) {
                    throw new DuplicatedResult("Position already exists");
                }
                else if (result.getDriver() == driver) {
                    throw new DuplicatedResult("Driver result already exists");
                }
            }
            newResults.get(i).setRace(raceA);
            newResults.get(i).setDriver(driver);
            resultRepository.save(newResults.get(i));
        }
        return raceA;
    }

    public Result findById(Long resultId) {
        return resultRepository.findById(resultId).orElseThrow(ResultNotFound::new);
    }

    public void deleteById(Long id) {
        resultRepository.deleteById(id);
    }

    public Result update(CreateResultRequest resultReq, Long id) {
        Result result = this.findById(id);
        Driver driver = driverRepository.findByName(resultReq.getDriverName()).orElseThrow(DriverNotFound::new);
        result.setDriver(driver);
        result.setPosition(resultReq.getPosition());
    return resultRepository.save(result);
    }
}
