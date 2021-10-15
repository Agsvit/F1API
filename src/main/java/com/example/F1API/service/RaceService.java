package com.example.F1API.service;

import com.example.F1API.controller.request.create.CreateRaceRequest;
import com.example.F1API.exception.RaceNotFound;
import com.example.F1API.model.Race;
import com.example.F1API.repository.RaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaceService {

    private final RaceRepository raceRepository;

    public RaceService(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    public List<Race> findAll() {
        return raceRepository.findAll();
    }

    public Race save(Race newRace) {
        return raceRepository.save(newRace);
    }

    public void deleteById(Long id) {
        raceRepository.deleteById(id);
    }

    public Race update(CreateRaceRequest raceReq, Long id) {
        Race race = this.findById(id);
        race.setTrack(raceReq.getTrack());
        race.setDate(raceReq.getDate());
        return raceRepository.save(race);
    }

    public Race findById(Long raceId) {
        return raceRepository.findById(raceId).orElseThrow(RaceNotFound::new);
    }
}
