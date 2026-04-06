package lk.practice.candidate_recruitment_tacker.insfrastructure.persistence;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lk.practice.candidate_recruitment_tacker.domain.model.CandidateStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "candidates")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Enumerated(EnumType.STRING)
    private CandidateStatus status;

    private boolean isDeleted = false;

}