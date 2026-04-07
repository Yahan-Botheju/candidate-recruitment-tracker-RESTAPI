package lk.practice.candidate_recruitment_tacker.insfrastructure.persistence;

import lk.practice.candidate_recruitment_tacker.domain.repository.CandidateRepository;
import lk.practice.candidate_recruitment_tacker.insfrastructure.persistence.persistenceMappers.CandidatePersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CandidateImpl implements CandidateRepository {

    //inject jpa repo
    private final JpaCandidateRepository jpaCandidateRepository;

    //inject persistence mapper
    private final CandidatePersistenceMapper candidatePersistenceMapper;
}