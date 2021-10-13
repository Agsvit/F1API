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
public class CreateDriverRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Age should be between 1 and 99")
    private int age;
    private Long carId;
}