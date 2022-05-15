package com.football.manager.model;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "player")
public class Player extends RepresentationModel<Player> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_player")
    private int id;
    private String name;
    @Column(name = "second_name")
    private String secondName;
    private LocalDate birthday;
    @Column(name = "career_start_date")
    private LocalDate careerStartDate;
    @Enumerated(EnumType.STRING)
    private Position position;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="team_id")
    private Team team;
}
