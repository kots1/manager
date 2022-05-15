package com.football.manager.service;

import com.football.manager.model.Transfer;

import java.util.List;

public interface TransferService {
    List<Transfer> getAllTransfers(int page);

    Transfer getTransferById(int id);

    Transfer createTransfer(int playerId, int buyerId);

    void deleteTransfer(int id);

    int calculatePrice(int playerId);

    List<Transfer> getTeamTransfer(int id);

    List<Transfer> getPlayerTransfer(int id);
}
