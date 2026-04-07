package lk.practice.candidate_recruitment_tacker.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Candidate {

    private Long id;
    private String fullName;
    private String email;
    private int experience;
    private String appliedRole;
    private double expectedSalary;
    private CandidateStatus status;
    private boolean isDeleted = false;
}