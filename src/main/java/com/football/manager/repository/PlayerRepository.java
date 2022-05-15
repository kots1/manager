package com.football.manager.repository;

import com.football.manager.model.Player;
import com.football.manager.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PlayerRepository extends PagingAndSortingRepository<Player,Integer> {

  List<Player> findAllByTeam(Team team);
}
