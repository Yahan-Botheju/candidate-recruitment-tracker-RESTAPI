package lk.practice.candidate_recruitment_tracker.web.controllers;

import jakarta.validation.Valid;
import lk.practice.candidate_recruitment_tracker.domain.model.Candidate;
import lk.practice.candidate_recruitment_tracker.domain.model.CandidateStatus;
import lk.practice.candidate_recruitment_tracker.usecase.CandidateUseCase;
import lk.practice.candidate_recruitment_tracker.usecase.records.CreateNewCandidateCommand;
import lk.practice.candidate_recruitment_tracker.usecase.records.CreateNewCandidateResult;
import lk.practice.candidate_recruitment_tracker.web.DTOs.CandidateRequestDTO;
import lk.practice.candidate_recruitment_tracker.web.DTOs.CandidateResponseDTO;
import lk.practice.candidate_recruitment_tracker.web.DTOs.CreateNewCandidateRequestDTO;
import lk.practice.candidate_recruitment_tracker.web.DTOs.CreateNewCandidateResponseDTO;
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

    //inject required dependencies
    private final CandidateUseCase candidateUseCase;
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
                .map(candidate -> {

                    //turn entity to model
                    CandidateResponseDTO responseDTO = candidateWebMapper.toResponseDTO(candidate);

                    //check role == admin or not
                    if(!"ADMIN".equalsIgnoreCase(role)){
                        responseDTO.setExpectedSalary(0.0);
                    }

                    //return response
                    return responseDTO;

                }).toList();
    }

    //save candidate
    @PostMapping("/register")
    public ResponseEntity<CreateNewCandidateResponseDTO> saveCandidate(
            @Valid @RequestBody CreateNewCandidateRequestDTO createNewCandidateRequestDTO
            ){
        //to command through the mapper
        CreateNewCandidateCommand toCommand = candidateWebMapper.toCreateNewCandidateCommand(createNewCandidateRequestDTO);
        //to usecase
        CreateNewCandidateResult toResult = candidateUseCase.saveCandidate(toCommand);
        //to response through mapper
        CreateNewCandidateResponseDTO responseDTO = candidateWebMapper.toCreateNewCandidateResponse(toResult);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
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