package lk.practice.candidate_recruitment_tacker.web.DTOs;

import lk.practice.candidate_recruitment_tacker.domain.model.CandidateStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateResponseDTO {

    private Long id;
    private String fullName;
    private String email;
    private int experience;
    private String appliedRole;
    private double expectedSalary;
    private CandidateStatus status;

    //add new field for scoring
    private double recruitmentScore;
}