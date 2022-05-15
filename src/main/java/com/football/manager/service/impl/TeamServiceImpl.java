package com.football.manager.service.impl;

import com.football.manager.model.Team;
import com.football.manager.repository.TeamRepository;
import com.football.manager.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    @Override
    public List<Team> getAllTeams(int page) {
        log.info("TeamServiceImpl getAllTeams()");
        Pageable pageable = PageRequest.of(page,5);
        return teamRepository.findAll(pageable).getContent();
    }

    @Override
    public Team getTeamById(int id) {
        log.info("TeamServiceImpl getTeamById() id =" + id);
        return teamRepository.findById(id).orElseThrow();
    }

    @Override
    public Team createTeam(Team team) {
        log.info("TeamServiceImpl createTeam()");
        return teamRepository.save(team);
    }

    @Override
    public void deleteTeam(int id) {
        log.info("TeamServiceImpl deleteTeam()");
        Team team = teamRepository.findById(id).orElseThrow();
        teamRepository.delete(team);
    }

    @Override
    public Team updateTeam(int id, Team team) {
        log.info("TeamServiceImpl getAllTeams()");
        teamRepository.findById(id).orElseThrow();
        team.setId(id);
        return teamRepository.save(team);
    }
}
