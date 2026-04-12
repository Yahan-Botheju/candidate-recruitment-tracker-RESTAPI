package lk.practice.candidate_recruitment_tacker.insfrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCandidateRepository extends JpaRepository<CandidateEntity, Long> {
}