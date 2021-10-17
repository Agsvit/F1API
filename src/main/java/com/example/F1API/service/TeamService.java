package com.example.F1API.service;

import com.example.F1API.controller.request.create.CreateTeamRequest;
import com.example.F1API.exception.TeamNotFound;
import com.example.F1API.model.Team;
import com.example.F1API.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }
    //Looks for a driver by id and returns a Driver object or throws exceptions (instead of returning an Optional)
    public Team findById(Long teamId) {
        return teamRepository.findById(teamId).orElseThrow(TeamNotFound::new);
    }

    public <S extends Team> S save(S newTeam) {
        return teamRepository.save(newTeam);
    }

    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }

    public Team update(CreateTeamRequest teamReq, Long id) {
        Team team = this.findById(id);
        team.setName(teamReq.getName());
        team.setPrincipal(teamReq.getPrincipal());
        return teamRepository.save(team);
    }

    public Team findByName(String name) {
        return teamRepository.findByName(name).orElseThrow(TeamNotFound::new);
    }
}
