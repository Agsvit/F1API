package com.example.F1API.controller.request.create;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateResultRequest {

//    private Long raceId;
    private Long driverId;
    private int position;

}