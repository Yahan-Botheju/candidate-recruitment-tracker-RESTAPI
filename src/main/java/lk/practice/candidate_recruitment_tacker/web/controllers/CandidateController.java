package lk.practice.candidate_recruitment_tacker.web.controllers;

import lk.practice.candidate_recruitment_tacker.usecase.CandidateUseCase;
import lk.practice.candidate_recruitment_tacker.web.webMappers.CandidateWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/candidates")
@RequiredArgsConstructor
public class CandidateController {

    //inject candidate usecase
    private final CandidateUseCase candidateUseCase;

    //inject web mapper
    private final CandidateWebMapper candidateWebMapper;
}