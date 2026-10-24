package lk.practice.candidate_recruitment_tracker.insfrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaCandidateRepository extends JpaRepository<CandidateEntity, Long> {

    Optional<CandidateEntity> getCandidateByEmail(String email);
}