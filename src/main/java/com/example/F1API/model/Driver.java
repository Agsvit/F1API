package com.example.F1API.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @Min(value = 1)
    @Max(value = 99)
    private int age;

    @ManyToOne
    @JoinColumn(name = "id_team")
    private Team team;

//    @ManyToOne
//    @JoinColumn(name = "id_race")
//    private Race race;
}
