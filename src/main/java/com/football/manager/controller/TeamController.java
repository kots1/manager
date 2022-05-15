package com.football.manager.controller;

import com.football.manager.model.Team;
import com.football.manager.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/football")
public class TeamController {
    private final TeamService teamService;

    @GetMapping("/team")
    @ResponseStatus(HttpStatus.OK)
    public List<Team> getAllTeams(@RequestParam(defaultValue = "1")  String page) {
        log.info("TeamController getAllTeams");
        return teamService.getAllTeams(Integer.parseInt(page)-1);
    }

    @GetMapping("/team/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Team getTeamById(@PathVariable int id) {
        log.info("TeamController getTeamById id = " + id);
        return teamService.getTeamById(id);
    }



    @PostMapping("/team")
    @ResponseStatus(HttpStatus.CREATED)
    public Team createTeam(@RequestBody @Valid Team team) {
        log.info("TeamController createTeam ");
        return teamService.createTeam(team);
    }

    @DeleteMapping("/team/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTeam(@PathVariable int id) {
        log.info("TeamController deleteTeam ");
        teamService.deleteTeam(id);
    }

    @PutMapping("/team/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Team updateTeam(@PathVariable int id, @RequestBody @Valid Team team) {
        log.info("TeamController updateTeam ");
        return teamService.updateTeam(id, team);
    }
}
