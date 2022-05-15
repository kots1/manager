package com.football.manager.controller;

import com.football.manager.model.Team;
import com.football.manager.model.Transfer;
import com.football.manager.service.TransferService;
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
public class TransferController {
    private final TransferService transferService;

    @GetMapping("/transfer")
    @ResponseStatus(HttpStatus.OK)
    public List<Transfer> getAllTransfers(@RequestParam(defaultValue = "1")  String page) {
        log.info("TransferController getAllTransfers");
        return transferService.getAllTransfers(Integer.parseInt(page)-1);
    }

    @GetMapping("/transfer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Transfer getTransferById(@PathVariable int id) {
        log.info("TransferController getTransferById id = " + id);
        return transferService.getTransferById(id);
    }

    @PostMapping("/team/{id}/transfer")
    @ResponseStatus(HttpStatus.CREATED)
    public Transfer buyPlayer (@PathVariable int id, @RequestParam  int playerId) {
        log.info("TeamController getTeamById id = " + id);
        return transferService.createTransfer(playerId,id);
    }
    @GetMapping("/team/{id}/transfer")
    @ResponseStatus(HttpStatus.OK)
    public List<Transfer> getTeamTransfer (@PathVariable int id) {
        log.info("TeamController getTeamTransfer id = " + id);
        return transferService.getTeamTransfer(id);
    }

    @GetMapping("/player/{id}/transfer")
    @ResponseStatus(HttpStatus.OK)
    public List<Transfer> getPlayerTransfer (@PathVariable int id) {
        log.info("TeamController getPlayerTransfer id = " + id);
        return transferService.getPlayerTransfer(id);
    }

    @DeleteMapping("/transfer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTransfer(@PathVariable int id) {
        log.info("TransferController deleteTransfer ");
        transferService.deleteTransfer(id);
    }

}
