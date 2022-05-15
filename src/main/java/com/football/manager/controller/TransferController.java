package com.football.manager.controller;

import com.football.manager.model.Transfer;
import com.football.manager.service.TransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/football")
public class TransferController {
    private final TransferService transferService;

    @GetMapping("/transfer")
    @ResponseStatus(HttpStatus.OK)
    public List<Transfer> getAllTransfers(@RequestParam(defaultValue = "1") String page) {
        log.info("TransferController getAllTransfers");
        List<Transfer> transfers = transferService.getAllTransfers(Integer.parseInt(page) - 1);
        transfers.forEach(this::addHateoasLinks);
        return transfers;
    }

    @GetMapping("/transfer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Transfer getTransferById(@PathVariable int id) {
        log.info("TransferController getTransferById id = " + id);
        Transfer transfer = transferService.getTransferById(id);
        addHateoasLinks(transfer);
        return transfer;
    }

    @PostMapping("/team/{id}/transfer")
    @ResponseStatus(HttpStatus.CREATED)
    public Transfer buyPlayer(@PathVariable int id, @RequestParam int playerId) {
        log.info("TeamController getTeamById id = " + id);
        Transfer transfer = transferService.createTransfer(playerId, id);
        addHateoasLinks(transfer);
        return transfer;
    }

    @GetMapping("/team/{id}/transfer")
    @ResponseStatus(HttpStatus.OK)
    public List<Transfer> getTeamTransfer(@PathVariable int id) {
        log.info("TeamController getTeamTransfer id = " + id);
        List<Transfer> transfers = transferService.getTeamTransfer(id);
        transfers.forEach(this::addHateoasLinks);
        return transfers;
    }

    @GetMapping("/player/{id}/transfer")
    @ResponseStatus(HttpStatus.OK)
    public List<Transfer> getPlayerTransfer(@PathVariable int id) {
        log.info("TeamController getPlayerTransfer id = " + id);
        List<Transfer> transfers = transferService.getPlayerTransfer(id);
        transfers.forEach(this::addHateoasLinks);
        return transfers;
    }

    @DeleteMapping("/transfer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTransfer(@PathVariable int id) {
        log.info("TransferController deleteTransfer ");
        transferService.deleteTransfer(id);
    }

    private void addHateoasLinks(Transfer transfer) {
        if (!transfer.hasLinks()) {
            Link link = linkTo(methodOn(TransferController.class).getTransferById(transfer.getId()))
                    .withSelfRel();
            transfer.add(link);
        }
    }
}
