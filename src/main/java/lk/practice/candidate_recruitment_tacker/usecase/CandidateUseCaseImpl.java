package lk.practice.candidate_recruitment_tacker.usecase;

import lk.practice.candidate_recruitment_tacker.domain.model.Candidate;
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
}