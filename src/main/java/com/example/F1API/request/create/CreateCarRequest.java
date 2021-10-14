package com.example.F1API.request.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCarRequest {

    @NotBlank(message = "Name is mandatory")
    private String model;
    @NotBlank(message = "Color1 is mandatory")
    private String color1;
    private String color2;
    private Long teamId;
}