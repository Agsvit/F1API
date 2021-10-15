package com.example.F1API.controller.request.create;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRaceRequest {

    private String track;
    private Date date;
}
