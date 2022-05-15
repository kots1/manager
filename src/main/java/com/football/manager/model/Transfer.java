package com.football.manager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "transfer")
public class Transfer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transfer")
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player")
    private Player player;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_team")
    private Team sellerTeam;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_team")
    private Team buyerTeam;
    @Column(name ="sales_cost")
    private int cost;
    @Column(name ="date")
    private LocalDate date;
}
