package com.football.manager.controller;

import com.football.manager.model.Team;
import com.football.manager.model.Transfer;
import com.football.manager.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/football")
public class TeamController {
    private final TeamService teamService;

    @GetMapping("/team")
    @ResponseStatus(HttpStatus.OK)
    public List<Team> getAllTeams(@RequestParam(defaultValue = "1") String page) {
        log.info("TeamController getAllTeams");
        List<Team> teams = teamService.getAllTeams(Integer.parseInt(page) - 1);
        teams.forEach(this::addHateoasLinks);
        return teams;
    }

    @GetMapping("/team/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Team getTeamById(@PathVariable int id) {
        log.info("TeamController getTeamById id = " + id);
        Team team = teamService.getTeamById(id);
        addHateoasLinks( team);
        return team;
    }


    @PostMapping("/team")
    @ResponseStatus(HttpStatus.CREATED)
    public Team createTeam(@RequestBody @Valid Team team) {
        log.info("TeamController createTeam ");
        team = teamService.createTeam(team);
        addHateoasLinks(team);
        return team;
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
        team = teamService.updateTeam(id, team);
        addHateoasLinks(team);
        return team;
    }

    private void addHateoasLinks(Team team) {
        if (!team.hasLinks()) {
            Link link = linkTo(methodOn(TeamController.class).getTeamById(team.getId()))
                    .withSelfRel();
            team.add(link);
            link = linkTo(methodOn(TransferController.class).getTeamTransfer(team.getId()))
                    .withRel("team transfer");
            team.add(link);
            link = linkTo(methodOn(PlayerController.class).getAllTeamPlayers(team.getId()))
                    .withRel("team players");
            team.add(link);
        }

    }
}
