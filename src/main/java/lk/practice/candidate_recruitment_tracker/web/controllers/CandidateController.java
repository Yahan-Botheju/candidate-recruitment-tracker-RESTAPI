package lk.practice.candidate_recruitment_tracker.web.controllers;

import lk.practice.candidate_recruitment_tracker.domain.model.Candidate;
import lk.practice.candidate_recruitment_tracker.domain.model.CandidateStatus;
import lk.practice.candidate_recruitment_tracker.usecase.CandidateUseCase;
import lk.practice.candidate_recruitment_tracker.web.DTOs.CandidateRequestDTO;
import lk.practice.candidate_recruitment_tracker.web.DTOs.CandidateResponseDTO;
import lk.practice.candidate_recruitment_tracker.web.webMappers.CandidateWebMapper;
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
    public List<CandidateResponseDTO> getAllCandidates(
            @RequestHeader(value = "X-User-Role",
                    required = false) String role,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        //get list of all candidates as model
        List<Candidate> candidates = candidateUseCase.getAllCandidates(page, size);

        //map all candidates and check role and hide salary
        return candidates.stream()
                .map(candidate ->
                    //use new mapper method for checking ADMIN or not
                     candidateWebMapper.checkIsAdmin(candidate, role)
                ).toList();
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

    //update candidate by id (ADMIN ONLY)
    @PutMapping("/status/{id}")
    public ResponseEntity<String> updateCandidateStatus(
            @PathVariable Long id,
            @RequestParam CandidateStatus candidateStatus,
            @RequestHeader(value = "X-User-Role",required = false) String role
            ){

        //call usecase interface's method and set values
        candidateUseCase.updateCandidateStatus(id, candidateStatus);

        return ResponseEntity.status(HttpStatus.CREATED).body("Candidate status updated successful!!");
    }
}