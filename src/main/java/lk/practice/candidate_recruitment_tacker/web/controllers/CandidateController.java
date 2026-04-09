package lk.practice.candidate_recruitment_tacker.web.controllers;

import lk.practice.candidate_recruitment_tacker.domain.model.Candidate;
import lk.practice.candidate_recruitment_tacker.usecase.CandidateUseCase;
import lk.practice.candidate_recruitment_tacker.web.DTOs.CandidateRequestDTO;
import lk.practice.candidate_recruitment_tacker.web.DTOs.CandidateResponseDTO;
import lk.practice.candidate_recruitment_tacker.web.webMappers.CandidateWebMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //save candidate
    @PostMapping("/register")
    public ResponseEntity<String> saveCandidate(
            @RequestBody CandidateRequestDTO candidateRequestDTO
            ){

        //set to domain model using web mapper
        Candidate toDomainModel = candidateWebMapper.toDomainModel(candidateRequestDTO);

        //send domain model to usecase
        candidateUseCase.saveCandidate(toDomainModel);

        //above both in one line chaining
        /* candidateUseCase.saveCandidate(candidateWebMapper.toDomainModel(candidateRequestDTO)); */

        //get response
        return ResponseEntity.status(HttpStatus.CREATED).body("Candidate registered successful!!!!");
    }

    //update candidate
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCandidate(
            @PathVariable Long id,
            @RequestBody CandidateRequestDTO candidateRequestDTO
            ){
        //request DTO turn to domain model
        Candidate toDomainModel = candidateWebMapper.toDomainModel(candidateRequestDTO);

        //domain model sent to usecase
        candidateUseCase.updateCandidate(id, toDomainModel);

        //make response
        return ResponseEntity.status(HttpStatus.CREATED).body("Candidate details updated successful!!");
    }

    //delete candidate
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCandidate(
            @PathVariable Long id
    ){
        //directly set to usecase, because no Req body
        candidateUseCase.deleteCandidate(id);

        return ResponseEntity.ok("Candidate delete successful!!");
    }
}