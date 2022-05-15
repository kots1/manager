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
@Entity
@Builder
@Table(name = "transfer")
public class Transfer extends RepresentationModel<Transfer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transfer")
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player")
    private Player player;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_team")
    private Team sellerTeam;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "buyer_team")
    private Team buyerTeam;
    @Column(name ="sales_cost")
    private int cost;
    @Column(name ="date")
    private LocalDate date;
}
