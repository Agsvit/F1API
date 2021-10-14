package com.example.F1API.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="result")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Position is mandatory")
    private int position;

    @ManyToOne
    @JoinColumn(name = "id_race")
    private Race race;

    @ManyToOne
    @JoinColumn(name = "id_driver")
    private Driver driver;

}
