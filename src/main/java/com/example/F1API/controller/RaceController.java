package com.example.F1API.controller;


import com.example.F1API.controller.request.create.CreateRaceRequest;
import com.example.F1API.controller.request.create.CreateResultRequest;
import com.example.F1API.controller.request.response.ResponseRaceRequest;
import com.example.F1API.controller.request.response.ResponseRaceResultRequest;
import com.example.F1API.controller.request.response.ResponseResultRequest;
import com.example.F1API.model.Race;
import com.example.F1API.model.Result;
import com.example.F1API.service.RaceService;
import com.example.F1API.service.ResultService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RaceController {

    private final RaceService raceService;
    private final ResultService resultService;

    public RaceController(RaceService raceService, ResultService resultService) {
        this.raceService = raceService;
        this.resultService = resultService;
    }

    @GetMapping("/races")
    public List<ResponseRaceRequest> getRaces() {
        List<ResponseRaceRequest> createRaceReq = new ArrayList<>();
        List<Race> races = raceService.findAll();
        for (Race race : races) {
            createRaceReq.add(new ResponseRaceRequest(
                    race.getId(),
                    race.getTrack(),
                    race.getDate()));
        }
        return createRaceReq;
    }

    @GetMapping("/race/{raceId}/results")
    public ResponseRaceResultRequest getRaceResults(Long raceId) {
        Race race = raceService.findById(raceId);
        ResponseRaceResultRequest responseRaceRequest = new ResponseRaceResultRequest(race.getId(), race.getTrack(), race.getDate()
                , Collections.emptyList());
        for (Result result : race.getResults()) {
            ResponseResultRequest responseResultRequest = new ResponseResultRequest(
                    result.getId(),
                    result.getDriver().getId(),
                    result.getDriver().getName(),
                    result.getPosition());
            responseRaceRequest.getResultResponses().add(responseResultRequest);
        }
        return responseRaceRequest;
    }

    @PostMapping(value = "/races", consumes = "application/json", produces = "application/json")
    public ResponseRaceResultRequest createRace(@RequestBody @Valid CreateRaceRequest raceReq) {
        Race newRace = Race
                .builder()
                .track(raceReq.getTrack())
                .date(raceReq.getDate())
                .build();
        raceService.save(newRace);
        ResponseRaceResultRequest raceRequest = new ResponseRaceResultRequest();
        raceRequest.setId(newRace.getId());
        raceRequest.setTrack(newRace.getTrack());
        raceRequest.setDate(newRace.getDate());
        return raceRequest;
    }

    @PutMapping(value = "/races/{id}")
    public ResponseRaceRequest updateRace(@PathVariable(value = "id") Long id, @RequestBody CreateRaceRequest raceReq) {
        Race race = raceService.update(raceReq, id);
        ResponseRaceRequest raceRespReq = new ResponseRaceRequest(
                race.getId(),
                race.getTrack(),
                race.getDate());
        return raceRespReq;
    }

    @PutMapping(value = "/results/{id}")
    public ResponseResultRequest updateResult(@PathVariable(value = "id") Long id, @RequestBody CreateResultRequest resultReq) {
        Result result = resultService.update(resultReq, id);
        ResponseResultRequest resultRespReq = new ResponseResultRequest(
                result.getId(),
                result.getDriver().getId(),
                result.getDriver().getName(),
                result.getPosition());
        return resultRespReq;
    }

    @PostMapping(value = "/race/{id}/results")
    public ResponseRaceResultRequest insertResultsOnRace(@RequestBody List<CreateResultRequest> resultReqs, Long id) {
        List<Result> results = new ArrayList<>();
        List<Long> driversIds = new ArrayList<>();
        for (CreateResultRequest resultReq : resultReqs) {
            results.add(Result
                    .builder()
                    .position(resultReq.getPosition())
                    .build());
            driversIds.add(resultReq.getDriverId());
        }
        Race race = resultService.save(results, id, driversIds);
        ResponseRaceResultRequest responseRaceRequest = new ResponseRaceResultRequest(race.getId(), race.getTrack(), race.getDate()
                , Collections.emptyList());
        for (Result result : race.getResults()) {
            ResponseResultRequest responseResultRequest = new ResponseResultRequest(
                    result.getId(),
                    result.getDriver().getId(),
                    result.getDriver().getName(),
                    result.getPosition());
            responseRaceRequest.getResultResponses().add(responseResultRequest);
        }
        return responseRaceRequest;
    }

    @DeleteMapping(value = "/races/{id}")
    public void deleteRace(Long id) {
        raceService.deleteById(id);
    }

    @DeleteMapping(value = "/results/{id}")
    public void deleteResult(Long id) {
        resultService.deleteById(id);
    }


}
