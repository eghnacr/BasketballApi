package com.egehan.basketballapi.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "players")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"id"})
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "position")
    @Enumerated(value = EnumType.STRING)
    private Position position;
}
