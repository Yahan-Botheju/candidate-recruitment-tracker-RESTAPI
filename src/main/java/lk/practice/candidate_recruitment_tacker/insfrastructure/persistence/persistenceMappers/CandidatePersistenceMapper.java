package lk.practice.candidate_recruitment_tacker.insfrastructure.persistence.persistenceMappers;

import lk.practice.candidate_recruitment_tacker.domain.model.Candidate;
import lk.practice.candidate_recruitment_tacker.insfrastructure.persistence.CandidateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CandidatePersistenceMapper {

    //domain model to entity
    /*
      ignore the isDeleted field, that not contain in domain model but entity has
    */
    CandidateEntity toEntity(Candidate candidate);

    //entity to domain model
    Candidate toDomainModel(CandidateEntity candidateEntity);

    //copy new domain model data to old entity
    @Mapping(target = "id", ignore = true)
    CandidateEntity updateEntityFromNewDomainModel(Candidate candidate, @MappingTarget CandidateEntity candidateEntity);
}