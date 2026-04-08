package lk.practice.candidate_recruitment_tacker.web.controllers;

import lk.practice.candidate_recruitment_tacker.usecase.CandidateUseCase;
import lk.practice.candidate_recruitment_tacker.web.DTOs.CandidateResponseDTO;
import lk.practice.candidate_recruitment_tacker.web.webMappers.CandidateWebMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/candidates")
@RequiredArgsConstructor
public class CandidateController {

    //inject candidate usecase
    private final CandidateUseCase candidateUseCase;

    //inject web mapper
    private final CandidateWebMapper candidateWebMapper;

    //get all candidates
    @GetMapping("/all")
    public List<CandidateResponseDTO> getAllCandidates(){

        //get domain model through usecase, through web mapper turn that into response dto and return
        return candidateUseCase.getAllCandidates()
                .stream()
                .map(candidateWebMapper::toResponseDTO).toList();
    }
}