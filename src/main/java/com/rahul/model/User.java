package com.rahul.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private int id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;
}
