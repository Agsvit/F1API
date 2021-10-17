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
@Table(name="car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Model is mandatory")
    private String model;
    private String color1;
    private String color2;

    //1:1 relation with team since this each team only have one car (model)
    @OneToOne
    @JoinColumn(name = "id_team")
    private Team team;

}