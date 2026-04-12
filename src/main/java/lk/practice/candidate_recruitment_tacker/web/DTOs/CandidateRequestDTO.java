package lk.practice.candidate_recruitment_tacker.web.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateRequestDTO {
    @NotBlank(message = "Full name is required")
    private String fullName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @Min(value = 0, message = "Experience cannot be zero")
    private int experience;

    @NotBlank(message = "Applied role cannot be empty")
    private String appliedRole;

    @Positive(message = "Expected salary should be higher than zero")
    private double expectedSalary;
}