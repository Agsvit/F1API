package com.example.F1API.controller.request.response;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseTeamRequest {

    private Long id;
    private String name;
    private String principal;

}