package com.riwi.RiwiTech.application.dtos.responses;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    @NotBlank(message = "Name is required")
    private String username;

    @NotBlank(message = "Email is required")
    private String email;

}
