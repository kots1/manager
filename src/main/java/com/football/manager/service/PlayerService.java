package com.football.manager.service;

import com.football.manager.dto.PlayerDto;
import com.football.manager.model.Player;

import javax.validation.Valid;
import java.util.List;

public interface PlayerService {
    List<Player> getAllPlayers(int page);

    Player getPlayerById(int id);

    Player createPlayer(@Valid PlayerDto team);

    void deletePlayer(int id);

    Player updatePlayer(int id, @Valid PlayerDto player);

    List<Player> getAllTeamPlayers(int id);
}
