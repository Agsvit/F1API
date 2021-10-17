package com.example.F1API.controller.request.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseResultRequest {
    private Long id;
    private Long driverId;
    private String driverName;
    private int position;
}
