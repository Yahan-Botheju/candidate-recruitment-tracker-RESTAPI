package lk.practice.candidate_recruitment_tracker.web.webMappers;

import lk.practice.candidate_recruitment_tracker.domain.model.Candidate;
import lk.practice.candidate_recruitment_tracker.web.DTOs.CandidateRequestDTO;
import lk.practice.candidate_recruitment_tracker.web.DTOs.CandidateResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CandidateWebMapper {

    //request DTO to domain model
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "recruitmentScore", ignore = true)
    Candidate toDomainModel(CandidateRequestDTO candidateRequestDTO);

    //domain model response DTO
    CandidateResponseDTO toResponseDTO(Candidate candidate);
}