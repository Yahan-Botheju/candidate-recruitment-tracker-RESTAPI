package lk.practice.candidate_recruitment_tacker.domain.repository;

import lk.practice.candidate_recruitment_tacker.domain.model.Candidate;
import lk.practice.candidate_recruitment_tacker.insfrastructure.persistence.CandidateEntity;

import java.util.List;

public interface CandidateRepository {

    //get all candidates
    List<Candidate> getAllCandidates();

    //save candidate
    void saveCandidate(Candidate candidate);
}