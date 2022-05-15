package com.football.manager.service;

import com.football.manager.model.Team;

import java.util.List;

public interface TeamService {
    List<Team> getAllTeams(int page);

    Team getTeamById(int id);

    Team createTeam(Team team);

    void deleteTeam(int id);

    Team updateTeam(int id, Team team);
}
