package com.football.manager.controller;

import com.football.manager.dto.PlayerDto;
import com.football.manager.model.Player;
import com.football.manager.model.Position;
import com.football.manager.service.PlayerService;
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
public class PlayerController {
    private final PlayerService playerService;
    private final TransferService transferService;

    @GetMapping("/player")
    @ResponseStatus(HttpStatus.OK)
    public List<Player> getAllPlayers( @RequestParam(defaultValue = "1")  String page) {
        log.info("PlayerController getAllPlayers");
        return playerService.getAllPlayers(Integer.parseInt(page)-1);
    }

    @GetMapping("/team/{id}/player")
    @ResponseStatus(HttpStatus.OK)
    public List<Player> getAllTeamPlayers(@PathVariable int id) {
        log.info("PlayerController getAllTeamPlayers");
        return playerService.getAllTeamPlayers(id);
    }

    @GetMapping("/player/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Player getPlayerById(@PathVariable int id) {
        log.info("PlayerController getPlayerById id = " + id);
        return playerService.getPlayerById(id);
    }

    @GetMapping("/player/{id}/calculate")
    @ResponseStatus(HttpStatus.OK)
    public String calculatePrice(@PathVariable int id) {
        log.info("PlayerController getPlayerById id = " + id);
        return "Player price: " + transferService.calculatePrice(id);
    }

    @PostMapping("/player")
    @ResponseStatus(HttpStatus.CREATED)
    public Player createPlayer(@RequestBody @Valid PlayerDto playerDto) {
        log.info("PlayerController createPlayer ");
        return playerService.createPlayer(playerDto);
    }

    @DeleteMapping("/player/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePlayer(@PathVariable int id) {
        log.info("PlayerController deletePlayer ");
        playerService.deletePlayer(id);
    }

    @PutMapping("/player/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Player updatePlayer(@PathVariable int id, @RequestBody @Valid PlayerDto playerDto) {
        log.info("PlayerController updatePlayer ");
        return playerService.updatePlayer(id, playerDto);
    }

    @GetMapping("/player/position")
    @ResponseStatus(HttpStatus.OK)
    public Position[] getPosition() {
        log.info("PlayerController getPosition ");
        return Position.values();
    }
}
