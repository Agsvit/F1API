package com.example.F1API.request.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseResultRequest {
    private Long id;
    private Long raceId;
    private String raceTrack;
    private Long driverId;
    private String driverName;
    private int position;
}
