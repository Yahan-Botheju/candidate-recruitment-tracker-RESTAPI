package lk.practice.candidate_recruitment_tracker.usecase.records;

public record CreateNewCandidateResult(
        String id,
        String fullName,
        String email,
        int experience,
        String appliedRole,
        double expectedSalary,
        String status
) {
}
