package com.example.F1API.controller;


import com.example.F1API.model.Race;
import com.example.F1API.request.create.CreateRaceRequest;
import com.example.F1API.request.response.ResponseRaceRequest;
import com.example.F1API.service.RaceService;
import com.example.F1API.service.ResultService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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

/*    @GetMapping("/races/{id}")
    public CreateRaceRequest getRaces() {
        List<CreateRaceRequest> createRaceReq = new ArrayList<>();
        List<Race> races = raceService.findAll();
        for (Race race :races) {
            createRaceReq.add(new CreateRaceRequest(
                    race.getId(),
                    race.getTrack(),
                    race.getDate()));
        }
        return createRaceReq;
    }*/

    @PostMapping(value = "/races", consumes = "application/json", produces = "application/json")
    public ResponseRaceRequest createRace(@RequestBody @Valid CreateRaceRequest raceReq) {
        Race newRace = Race
                .builder()
                .track(raceReq.getTrack())
                .date(raceReq.getDate())
                .build();
        raceService.save(newRace);
        ResponseRaceRequest raceRequest = new ResponseRaceRequest();
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

    @DeleteMapping(value = "/races/{id}")
    public void deleteRace(Long id) {
        raceService.deleteById(id);
    }



}
