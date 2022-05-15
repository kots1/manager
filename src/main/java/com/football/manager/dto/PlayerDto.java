package com.football.manager.dto;

import com.football.manager.model.Position;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
public class PlayerDto {
    @NotBlank(message = "Name should not be empty")
    private String name;
    @NotBlank(message = "Second name should not be empty")
    private String secondName;
    @NotNull(message = "Birthday should not be null")
    private LocalDate birthday;
    @NotNull(message = "Career start date should not be null")
    private LocalDate careerStartDate;
    private int teamId;
    @NotNull(message = "Player should play on position")
    private Position position;
}
