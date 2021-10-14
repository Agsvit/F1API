package com.example.F1API.request.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseRaceRequest {
    private Long id;
    private String track;
    private Date date;
}
