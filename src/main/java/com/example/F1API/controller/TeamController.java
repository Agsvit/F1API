package com.example.F1API.controller;

import com.example.F1API.controller.request.create.CreateTeamRequest;
import com.example.F1API.controller.request.response.ResponseTeamRequest;
import com.example.F1API.model.Team;
import com.example.F1API.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping("/teams")
    public List<ResponseTeamRequest> getTeams() {
        List<ResponseTeamRequest> responseTeamReq = new ArrayList<>();
        List<Team> teams = teamService.findAll();
        for (Team team : teams) {
            responseTeamReq.add(new ResponseTeamRequest(
                    team.getId(),
                    team.getName(),
                    team.getPrincipal()));
        }
        return responseTeamReq;
    }

    //In the future this should list the team's drivers
    @GetMapping("/teams/{name}")
    public ResponseTeamRequest getTeamByName(String name) {
        Team team = teamService.findByName(name);
        ResponseTeamRequest teamRequest = new ResponseTeamRequest(
                team.getId(),
                team.getName(),
                team.getPrincipal());
        return teamRequest;
    }

    @PostMapping(value = "createTeam", consumes = "application/json", produces = "application/json")
    public ResponseTeamRequest createTeam(@RequestBody CreateTeamRequest teamReq) {
        Team newTeam = Team.builder()
                .name(teamReq.getName())
                .principal(teamReq.getPrincipal())
                .build();
        teamService.save(newTeam);
        ResponseTeamRequest teamRequest = new ResponseTeamRequest();
        teamRequest.setId(newTeam.getId());
        teamRequest.setName(newTeam.getName());
        teamRequest.setPrincipal(newTeam.getPrincipal());
        return teamRequest;
    }

    @PutMapping(value = "updateTeam/{id}", consumes = "application/json", produces = "application/json")
    public ResponseTeamRequest updateTeam(Long id, @RequestBody CreateTeamRequest teamReq) {
        Team team = teamService.update(teamReq, id);
        ResponseTeamRequest teamRespReq = new ResponseTeamRequest(
                team.getId(),
                team.getName(),
                team.getPrincipal());
        return teamRespReq;

    }

    @DeleteMapping(value = "/deleteTeam/{id}")
    public void deleteTeam(Long id) {
        teamService.deleteById(id);
    }
}
