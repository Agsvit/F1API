package com.example.F1API.controller;

import com.example.F1API.model.Team;
import com.example.F1API.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Validated
public class TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping("/getTeams")
    public List<Team> getTeams() {
        return teamService.findAll();
    }

    @GetMapping("/getTeamsId")
    public Optional<Team> getTeamId(Long teamId) {
        return teamService.findById(teamId);
    }

    @PostMapping(value = "createTeam", consumes = "application/json", produces = "application/json")
    public Team createTeam(@RequestBody Team team) {
        Team newTeam = Team.builder().name(team.getName()).principal(team.getPrincipal()).build();
        teamService.save(newTeam);
        return newTeam;
    }

    @PutMapping(value = "updateTeam/{id}", consumes = "application/json", produces = "application/json")
    public Team updateCountry(Long id, @RequestBody Team team) {
        System.out.println(id);
        Optional<Team> teamToUpdate = teamService.findById(id);
        if (teamToUpdate.isPresent()) {
            teamToUpdate.get().setName(team.getName());
            teamToUpdate.get().setPrincipal(team.getPrincipal());
            teamService.save(teamToUpdate.get());
            return teamToUpdate.get();
        } else {
            ResponseEntity.badRequest().body("Team not found");
            return null;
        }
    }

    @DeleteMapping(value = "/deleteTeam/{id}")
    public void deleteTeam(Long id) {
        teamService.deleteById(id);
    }
}
