package com.example.F1API.service;

import com.example.F1API.model.Team;
import com.example.F1API.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public Optional<Team> findById(Long teamId) {
        return teamRepository.findById(teamId);
    }

    public <S extends Team> S save(S newTeam) {
        return teamRepository.save(newTeam);
    }

    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }
}
