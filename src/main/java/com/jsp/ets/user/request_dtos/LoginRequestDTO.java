package com.jsp.ets.user.request_dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {

    @NotNull(message = "email cannot be null")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$")
    private String email;

    @NotNull(message = "password cannot be null")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "password must contain atleast 1 uppercase"
            + " 1 lower case and special character and min of 8 lengths")
    private String password;
}
