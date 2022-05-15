package com.football.manager.service.impl;

import com.football.manager.exception.TransferException;
import com.football.manager.model.Player;
import com.football.manager.model.Team;
import com.football.manager.model.Transfer;
import com.football.manager.repository.PlayerRepository;
import com.football.manager.repository.TeamRepository;
import com.football.manager.repository.TransferRepository;
import com.football.manager.service.TransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {
    private final TransferRepository transferRepository;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    @Override
    public List<Transfer> getAllTransfers(int page) {
        log.info("TransferServiceImpl getAllTransfers()");
        Pageable pageable = PageRequest.of(page,5);
        return transferRepository.findAll(pageable).getContent();
    }

    @Override
    public Transfer getTransferById(int id) {
        log.info("TransferServiceImpl getTransferById()");
        return transferRepository.findById(id).orElseThrow();
    }

    @Override
    public Transfer createTransfer(int playerId, int buyerId) {
        log.info("TransferServiceImpl createTransfer()");
        Player player = playerRepository.findById(playerId).orElseThrow();
        if (buyerId == player.getTeam().getId()) {
            throw new TransferException("Player already in your team");
        }
        Team seller = player.getTeam();
        Team buyer = teamRepository.findById(buyerId).orElseThrow();
        int cost = calculate(player);
        if (buyer.getBalance() < cost) {
            throw new TransferException("Balance is too low for transfer");
        }

        Transfer transfer = Transfer.builder()
                .player(player)
                .buyerTeam(buyer)
                .sellerTeam(seller)
                .date(LocalDate.now())
                .cost(cost)
                .build();
        player.setTeam(buyer);
        seller.setBalance(seller.getBalance() + cost);
        buyer.setBalance(buyer.getBalance() - cost);
        playerRepository.save(player);
        return transferRepository.save(transfer);
    }

    private int calculate(Player player) {
        int experience = (int) ChronoUnit.MONTHS.between(player.getCareerStartDate(), LocalDate.now());
        int age = (int) ChronoUnit.YEARS.between(player.getBirthday(), LocalDate.now());
        int cost = experience * 100000 / age;
        int commission = cost * player.getTeam().getCommission() / 100;
        return commission + cost;
    }

    @Override
    public void deleteTransfer(int id) {
        log.info("TransferServiceImpl deleteTransfer()");
        Transfer transfer = transferRepository.findById(id).orElseThrow();
        transferRepository.delete(transfer);
    }

    @Override
    public int calculatePrice(int playerId) {
        log.info("TransferServiceImpl calculatePrice()");
        Player player = playerRepository.findById(playerId).orElseThrow();
        return calculate(player);
    }

    @Override
    public List<Transfer> getTeamTransfer(int id) {
        Team team = teamRepository.findById(id).orElseThrow();
        return transferRepository.findAllByBuyerTeamOrSellerTeam(team,team);
    }

    @Override
    public List<Transfer> getPlayerTransfer(int id) {
        Player player = playerRepository.findById(id).orElseThrow();
        return transferRepository.findAllByPlayer(player);
    }
}
