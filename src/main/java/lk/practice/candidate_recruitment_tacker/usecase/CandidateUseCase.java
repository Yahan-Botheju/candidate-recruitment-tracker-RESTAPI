package lk.practice.candidate_recruitment_tacker.usecase;

import lk.practice.candidate_recruitment_tacker.domain.model.Candidate;

import java.util.List;

public interface CandidateUseCase {

    //get all candidates
    List<Candidate> getAllCandidates();

    //create candidate
    void saveCandidate(Candidate candidate);
}