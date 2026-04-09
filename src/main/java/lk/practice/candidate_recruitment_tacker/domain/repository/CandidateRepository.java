package lk.practice.candidate_recruitment_tacker.domain.repository;

import lk.practice.candidate_recruitment_tacker.domain.model.Candidate;

import java.util.List;

public interface CandidateRepository {

    //get all candidates
    List<Candidate> getAllCandidates();

    //save candidate
    void saveCandidate(Candidate candidate);

    //update candidate
    void updateCandidate(Long id, Candidate candidate);

    //delete candidate
    void deleteCandidate(Long id);
}