package com.example.F1API.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTeamRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Principal is mandatory")
    private String principal;
//    private Long carId;
}
