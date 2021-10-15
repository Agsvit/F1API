package com.example.F1API.controller.request.response;

import javassist.compiler.ast.Pair;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseRaceResultRequest {
    private Long id;
    private String track;
    private Date date;

    private List<ResponseResultRequest> resultResponses;
}
