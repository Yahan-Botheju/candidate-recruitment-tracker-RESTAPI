package lk.practice.candidate_recruitment_tracker.domain.repository;

import lk.practice.candidate_recruitment_tracker.domain.model.Candidate;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository {

    Optional<Candidate> getCandidateByEmail(String email);

    //initiate get all candidates
    List<Candidate> getAllCandidates(int page, int size);

    //initiate save candidate
    Candidate saveCandidate(Candidate candidate);

    //initiate update candidate
    void updateCandidate(Long id, Candidate candidate);

    //initiate delete candidate
    void deleteCandidate(Long id);

    //initiate get single candidate by id
    Candidate getCandidate(Long id);
}