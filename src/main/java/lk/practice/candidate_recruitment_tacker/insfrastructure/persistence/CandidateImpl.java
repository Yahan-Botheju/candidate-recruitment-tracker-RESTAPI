package lk.practice.candidate_recruitment_tacker.insfrastructure.persistence;

import lk.practice.candidate_recruitment_tacker.domain.model.Candidate;
import lk.practice.candidate_recruitment_tacker.domain.repository.CandidateRepository;
import lk.practice.candidate_recruitment_tacker.insfrastructure.persistence.persistenceMappers.CandidatePersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CandidateImpl implements CandidateRepository {

    //inject jpa repo
    private final JpaCandidateRepository jpaCandidateRepository;

    //inject persistence mapper
    private final CandidatePersistenceMapper candidatePersistenceMapper;

    //get all candidates
    @Override
    public List<Candidate> getAllCandidates(){
        //get all candidates
        List<CandidateEntity> candidateEntities = jpaCandidateRepository.findAll();

        //return as domain model candidates list
        return candidateEntities.stream()
                .map(candidatePersistenceMapper::toDomainModel).toList();
    }

    //create candidate
    @Override
    public void saveCandidate(Candidate candidate){
        //turn domain model into entity through persistence mapper
        CandidateEntity candidateEntity = candidatePersistenceMapper.toEntity(candidate);

        //save in db through jpa repo
        jpaCandidateRepository.save(candidateEntity);
    }
}