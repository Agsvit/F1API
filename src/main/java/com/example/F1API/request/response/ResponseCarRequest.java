package com.example.F1API.request.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseCarRequest {
    private Long id;
    private String model;
    private String color1;
    private String color2;
    private Long teamId;
    private String teamName;
}