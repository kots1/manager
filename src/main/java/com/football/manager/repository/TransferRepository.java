package com.football.manager.repository;

import com.football.manager.model.Player;
import com.football.manager.model.Team;
import com.football.manager.model.Transfer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TransferRepository extends PagingAndSortingRepository<Transfer, Integer> {
    List<Transfer> findAllByBuyerTeamOrSellerTeam(Team buyerTeam, Team sellerTeam);

    List<Transfer> findAllByPlayer(Player player);
}
