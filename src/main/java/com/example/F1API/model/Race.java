package com.example.F1API.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="race")
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Track is mandatory")
    private String track;
    private Date date;

    //Realation with intermidiate table results, ordering them by position
    @OneToMany(mappedBy = "race")
    @OrderBy("position")
    private List<Result> results;

    //One to one relation with drivers to define the driver that made the fastest lap in the race (not implemented yet)
    @OneToOne
    @JoinColumn(name = "fastest_driver_id")
    private Driver driver;

}
