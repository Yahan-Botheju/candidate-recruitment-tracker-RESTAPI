package lk.practice.candidate_recruitment_tacker.usecase;

import lk.practice.candidate_recruitment_tacker.domain.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CandidateUseCaseImpl implements CandidateUseCase{

    //inject domain repo for initiate class as bean
    private final CandidateRepository candidateRepository;
}