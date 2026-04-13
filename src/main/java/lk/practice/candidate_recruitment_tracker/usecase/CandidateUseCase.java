package lk.practice.candidate_recruitment_tracker.usecase;

import lk.practice.candidate_recruitment_tracker.domain.model.Candidate;
import lk.practice.candidate_recruitment_tracker.domain.model.CandidateStatus;

import java.util.List;

public interface CandidateUseCase {

    //get all candidates
    List<Candidate> getAllCandidates(int page, int size);

    //create candidate
    void saveCandidate(Candidate candidate);

    //update candidate
    void updateCandidate(Long id, Candidate candidate);

    //delete candidate
    void deleteCandidate(Long id);

    //check role is ADMIN before show salary
    Candidate isAdmin(Candidate candidate, String role);

    //initiate update candidate method use for controller
    void updateCandidateStatus(Long id, CandidateStatus candidateStatus);
}