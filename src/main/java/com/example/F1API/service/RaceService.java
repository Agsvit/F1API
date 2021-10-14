package com.example.F1API.service;

import com.example.F1API.model.Race;
import com.example.F1API.repository.RaceRepository;
import com.example.F1API.repository.ResultRepository;
import com.example.F1API.request.create.CreateRaceRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RaceService {

    private final RaceRepository raceRepository;
    private final ResultRepository resultRepository;

    public RaceService(RaceRepository raceRepository, ResultRepository resultRepository) {
        this.raceRepository = raceRepository;
        this.resultRepository = resultRepository;
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
        Optional<Race> raceOptional = raceRepository.findById(id);
        if (raceOptional.isPresent()) {
            raceOptional.get().setTrack(raceReq.getTrack());
            raceOptional.get().setDate(raceReq.getDate());
            return raceRepository.save(raceOptional.get());
        }
        return null;
    }
}
