package com.football.manager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "team")
public class Team extends RepresentationModel<Team> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_team")
    private int id;
    @NotBlank(message = "Name should not be empty")
    private String name;
    @Positive(message = "Name should be positive")
    private int balance;
    @Max( value = 10 ,message = "Commission should be between 0 and 10")
    private Integer commission;
    @NotBlank(message = "Coach should not be empty")
    private String coach;
    @NotBlank(message = "Country should not be empty")
    private String country;
    @NotBlank(message = "League should not be empty")
    private String league;
}
