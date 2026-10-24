package lk.practice.candidate_recruitment_tracker.insfrastructure.persistence.jpa;

import lk.practice.candidate_recruitment_tracker.insfrastructure.persistence.entity.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaCandidateRepository extends JpaRepository<CandidateEntity, Long> {

    Optional<CandidateEntity> getCandidateByEmail(String email);
}