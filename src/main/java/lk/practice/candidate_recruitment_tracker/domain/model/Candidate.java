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

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public int getExperience() {
        return experience;
    }

    public String getAppliedRole() {
        return appliedRole;
    }

    public double getExpectedSalary() {
        return expectedSalary;
    }

    public CandidateStatus getStatus() {
        return status;
    }

    public double getRecruitmentScore() {
        return recruitmentScore;
    }


    /* __FACTORY_METHOD__ */

    public static Candidate createCandidate(
            String fullName,
            String email,
            int experience,
            String appliedRole,
            double expectedSalary,
            CandidateStatus status,
            double recruitmentScore
    ) {
        return new Candidate(
                null,
                fullName,
                email,
                experience,
                appliedRole,
                expectedSalary,
                status,
                recruitmentScore
        );
    }

    /* __DOMAIN_LOGICS__ */

    //calculate recruitment score
    public void recruitmentScoreCalculate(){
        if(experience < 0 || expectedSalary < 0.0){
            throw new IllegalArgumentException("Negative values are not allowed!!");
        }
        double experiencePoints = this.experience * 10;
        //calculate salary points
        double salaryPoints = this.expectedSalary > 500000
                ?  (int)((this.expectedSalary - 500000) / 100000) * 5
                : 0;
        this.recruitmentScore = experiencePoints - salaryPoints;
    }
}