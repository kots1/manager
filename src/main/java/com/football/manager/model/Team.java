package com.football.manager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "team")
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_team")
    private int id;
    @NotBlank(message = "Name should not be empty")
    private String name;
    @Positive(message = "Name should be positive")
    private int balance;
    //@Length( max = 10,message = "Commission should be between 0 and 10")
    private int commission;
    @NotBlank(message = "Coach should not be empty")
    private String coach;
    @NotBlank(message = "Country should not be empty")
    private String country;
    @NotBlank(message = "League should not be empty")
    private String league;
}
