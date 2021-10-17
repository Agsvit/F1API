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

    //This response request is used on the endpoint that lists the results for one race
    //It has this structure in order to show the client the attributes of the race on the top and then a list
    //with the results and the attributes of the results specified in the ResponseResultRequest
    private Long id;
    private String track;
    private Date date;

    private List<ResponseResultRequest> resultResponses;
}
