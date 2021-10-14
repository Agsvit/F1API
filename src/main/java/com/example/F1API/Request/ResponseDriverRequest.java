package com.example.F1API.Request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDriverRequest {


    private String name;
    private int age;
    private Long teamId;
    private String teamName;
}