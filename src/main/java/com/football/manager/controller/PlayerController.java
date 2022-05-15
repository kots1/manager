package com.football.manager.controller;

import com.football.manager.dto.PlayerDto;
import com.football.manager.model.Player;
import com.football.manager.model.Position;
import com.football.manager.service.PlayerService;
import com.football.manager.service.TransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/football")
public class PlayerController {
    private final PlayerService playerService;
    private final TransferService transferService;

    @GetMapping("/player")
    @ResponseStatus(HttpStatus.OK)
    public List<Player> getAllPlayers(@RequestParam(defaultValue = "1") String page) {
        log.info("PlayerController getAllPlayers");
        List<Player> players = playerService.getAllPlayers(Integer.parseInt(page) - 1);
        players.forEach(this::addHateoasLinks);
        return players;
    }

    @GetMapping("/team/{id}/player")
    @ResponseStatus(HttpStatus.OK)
    public List<Player> getAllTeamPlayers(@PathVariable int id) {
        log.info("PlayerController getAllTeamPlayers");
        List<Player> players = playerService.getAllTeamPlayers(id);
        players.forEach(this::addHateoasLinks);
        return players;
    }

    @GetMapping("/player/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Player getPlayerById(@PathVariable int id) {
        log.info("PlayerController getPlayerById id = " + id);
        Player players = playerService.getPlayerById(id);
        addHateoasLinks(players);
        return players;
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
        Player player = playerService.createPlayer(playerDto);
        addHateoasLinks(player);
        return player;
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
        Player player = playerService.updatePlayer(id, playerDto);
        addHateoasLinks(player);
        return player;
    }

    @GetMapping("/player/position")
    @ResponseStatus(HttpStatus.OK)
    public Position[] getPosition() {
        log.info("PlayerController getPosition ");
        return Position.values();
    }

    private void addHateoasLinks(Player player) {
        if (!player.hasLinks()) {
            Link link = linkTo(methodOn(PlayerController.class).getPlayerById(player.getId()))
                    .withSelfRel();
            player.add(link);
            link = linkTo(methodOn(TransferController.class).getPlayerTransfer(player.getId()))
                    .withRel("player transfer");
            player.add(link);
        }

    }
}
