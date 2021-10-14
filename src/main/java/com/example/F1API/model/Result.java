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
    @NotBlank(message = "Track is mandatory")
    private String track;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "id_race")
    private Race race;

    @OneToOne
    @JoinColumn(name = "id_driver")
    private Driver driver;

}
