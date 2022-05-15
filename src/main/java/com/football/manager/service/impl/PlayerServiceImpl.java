package com.football.manager.service.impl;

import com.football.manager.dto.PlayerDto;
import com.football.manager.model.Player;
import com.football.manager.model.Team;
import com.football.manager.repository.PlayerRepository;
import com.football.manager.service.PlayerService;
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
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamService teamService;

    @Override
    public List<Player> getAllPlayers(int page) {
        log.info("PlayerServiceImpl getAllPlayers");
        Pageable pageable = PageRequest.of(page,5);
        return playerRepository.findAll(pageable).getContent();
    }

    @Override
    public Player getPlayerById(int id) {
        log.info("PlayerServiceImpl getPlayerById id = "+id);
        return playerRepository.findById(id).orElseThrow();
    }

    @Override
    public Player createPlayer(PlayerDto playerDto) {
        log.info("PlayerServiceImpl createPlayer ");

        Team team = teamService.getTeamById(playerDto.getTeamId());
        Player player = Player.builder()
                .birthday(playerDto.getBirthday())
                .careerStartDate(playerDto.getCareerStartDate())
                .name(playerDto.getName())
                .team(team)
                .secondName(playerDto.getSecondName())
                .position(playerDto.getPosition())
                .build();
        return playerRepository.save(player);
    }

    @Override
    public void deletePlayer(int id) {
        log.info("PlayerServiceImpl deletePlayer ");
        Player player = playerRepository.findById(id).orElseThrow();
        playerRepository.delete(player);
    }

    @Override
    public Player updatePlayer(int id, PlayerDto playerDto) {
        log.info("PlayerServiceImpl deletePlayer ");
        Team team = teamService.getTeamById(playerDto.getTeamId());
        Player player = Player.builder()
                .id(id)
                .birthday(playerDto.getBirthday())
                .careerStartDate(playerDto.getCareerStartDate())
                .name(playerDto.getName())
                .team(team)
                .secondName(playerDto.getSecondName())
                .position(playerDto.getPosition())
                .build();
        return playerRepository.save(player);

    }

    @Override
    public List<Player> getAllTeamPlayers(int id) {
        log.info("PlayerServiceImpl getAllTeamPlayers ");
        Team team =teamService.getTeamById(id);
        return playerRepository.findAllByTeam(team);
    }
}
