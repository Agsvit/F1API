package com.example.F1API.request.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDriverRequest {

    private Long id;
    private String name;
    private int age;
    private Long teamId;
    private String teamName;
}