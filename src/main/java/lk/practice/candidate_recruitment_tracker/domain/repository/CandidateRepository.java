package lk.practice.candidate_recruitment_tracker.domain.repository;

import lk.practice.candidate_recruitment_tracker.domain.model.Candidate;

import java.util.List;

public interface CandidateRepository {

    //initiate get all candidates
    List<Candidate> getAllCandidates(int page, int size);

    //initiate save candidate
    void saveCandidate(Candidate candidate);

    //initiate update candidate
    void updateCandidate(Long id, Candidate candidate);

    //initiate delete candidate
    void deleteCandidate(Long id);

    //initiate get single candidate by id
    Candidate getCandidate(Long id);
}