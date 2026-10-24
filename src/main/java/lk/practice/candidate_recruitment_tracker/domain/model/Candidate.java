package lk.practice.candidate_recruitment_tracker.domain.model;


public class Candidate {
    private Long id;
    private String fullName;
    private String email;
    private int experience;
    private String appliedRole;
    private double expectedSalary;
    private CandidateStatus status;

    //add new field for scoring, shows only
    private double recruitmentScore;


    public Candidate(Long id, String fullName, String email, int experience, String appliedRole, double expectedSalary, CandidateStatus status, double recruitmentScore) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.experience = experience;
        this.appliedRole = appliedRole;
        this.expectedSalary = expectedSalary;
        this.status = status;
        this.recruitmentScore = recruitmentScore;
    }

    public Long getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public int getExperience() { return experience; }
    public String getAppliedRole() { return appliedRole; }
    public double getExpectedSalary() { return expectedSalary; }
    public CandidateStatus getStatus() { return status; }
    public double getRecruitmentScore() { return recruitmentScore; }}