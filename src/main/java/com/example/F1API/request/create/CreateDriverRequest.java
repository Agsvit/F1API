package com.example.F1API.request.create;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateDriverRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Age should be between 1 and 99")
    private int age;
    private Long teamId;
}