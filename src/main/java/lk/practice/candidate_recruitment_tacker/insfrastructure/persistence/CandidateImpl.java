package lk.practice.candidate_recruitment_tacker.insfrastructure.persistence;

import lk.practice.candidate_recruitment_tacker.domain.model.Candidate;
import lk.practice.candidate_recruitment_tacker.domain.model.CandidateStatus;
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

    //update candidate
    @Override
    public  void updateCandidate(Long id, Candidate candidate){
        //check available of ID given user
        CandidateEntity candidateEntity = jpaCandidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid ID" + id));

        //check candidate status
         if(candidateEntity.getStatus() == CandidateStatus.HIRED || candidateEntity.getStatus() == CandidateStatus.REJECTED){
             throw new RuntimeException("Invalid status");
         }

        //copy new domain model data into old data entity
       CandidateEntity updatedEntity = candidatePersistenceMapper
               .updateEntityFromNewDomainModel(candidate, candidateEntity);

       //save in db
        jpaCandidateRepository.save(updatedEntity);
    }

    //delete candidate
    @Override
    public void deleteCandidate(Long id){
        //check candidate availability according to ID
        if(!jpaCandidateRepository.existsById(id)){
            throw new RuntimeException("Invalid ID" + id);
        }

        //soft delete
        jpaCandidateRepository.deleteById(id);
    }


}