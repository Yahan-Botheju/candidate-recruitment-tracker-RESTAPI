package lk.practice.candidate_recruitment_tacker.usecase;

import lk.practice.candidate_recruitment_tacker.domain.model.Candidate;
import lk.practice.candidate_recruitment_tacker.domain.model.CandidateStatus;
import lk.practice.candidate_recruitment_tacker.domain.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CandidateUseCaseImpl implements CandidateUseCase{

    //inject domain repo for initiate class as bean
    private final CandidateRepository candidateRepository;

    //get all candidates
    @Override
    public List<Candidate> getAllCandidates(){
        return candidateRepository.getAllCandidates();
    }

    //save candidate
    @Override
    public void saveCandidate(Candidate candidate){
        //set status applied for fresh candidate
        candidate.setStatus(CandidateStatus.APPLIED);

        candidateRepository.saveCandidate(candidate);
    }

    //update candidate
    @Override
    public void updateCandidate(Long id, Candidate candidate){
        //set value to domain repo
        candidateRepository.updateCandidate(id, candidate);
    }

    //delete candidate
    @Override
    public void deleteCandidate(Long id){
        //soft delete by id
        candidateRepository.deleteCandidate(id);
    }
}