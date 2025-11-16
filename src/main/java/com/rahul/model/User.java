package com.rahul.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@Table(name = "user_details")
@Builder
public class User {

    protected User(){}

    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message = "Name cannot be blank")
    @JsonProperty("user_name")
    private String name;

    @Past(message = "Birth date must be in the past")
    @JsonProperty("birth_date")
    private LocalDate birthDate;
}
