package lk.practice.candidate_recruitment_tacker.insfrastructure.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "candidates")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateEntity {

    private Long id;
    private String fullName;
    private String email;
    private int experience;
    private String appliedRole;
    private double expectedSalary;
    private enum status{
        Applied,
        Shortlisted,
        Hired,
        Rejected
    }
    private boolean isDeleted;

}