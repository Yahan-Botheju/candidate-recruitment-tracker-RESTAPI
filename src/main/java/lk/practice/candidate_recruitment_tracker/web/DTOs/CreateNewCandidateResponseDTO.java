package lk.practice.candidate_recruitment_tracker.web.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateNewCandidateResponseDTO {
    private String id;
    private String fullName;
    private String email;
    private int experience;
    private String appliedRole;
    private double expectedSalary;
    private String status;
}
