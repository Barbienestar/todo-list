package com.itesm.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * CreateUserDto
 */
public class CreateUserDto {
    @Email(message = "Email is invalid")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Full name is required") private String fullName;
    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*\\W).+$",
        message = "Password must contain at least one uppercase, one lowercase, one number and one "
            + "special character")
    private String password;
    @NotBlank(message = "Role is required") private String role;

    public CreateUserDto() {}

    public CreateUserDto(String email, String fullName, String pasword) {
        this.email = email;
        this.fullName = fullName;
        this.password = pasword;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getFullName() { return fullName; }

    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }
}
