package lk.practice.candidate_recruitment_tracker.usecase.records;

public record CreateNewCandidateCommand(
        String fullName,
        String email,
        int experience,
        String appliedRole,
        double expectedSalary
) {
}
