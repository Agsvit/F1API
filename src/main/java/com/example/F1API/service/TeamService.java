package com.example.F1API.service;

import com.example.F1API.request.create.CreateTeamRequest;
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

    public Team findById(Long teamId) {
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isPresent()) {
            return team.get();
        }
        return null;
    }

    public <S extends Team> S save(S newTeam) {
        return teamRepository.save(newTeam);
    }

    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }

    public Team update(CreateTeamRequest teamReq, Long id) {
        Optional<Team> teamOptional = teamRepository.findById(id);
        if (teamOptional.isPresent()) {
            teamOptional.get().setName(teamReq.getName());
            teamOptional.get().setPrincipal(teamReq.getPrincipal());
            return teamRepository.save(teamOptional.get());
        }
        return null;
    }
}
