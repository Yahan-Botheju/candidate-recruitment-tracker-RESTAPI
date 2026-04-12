package lk.practice.candidate_recruitment_tracker.insfrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCandidateRepository extends JpaRepository<CandidateEntity, Long> {
}