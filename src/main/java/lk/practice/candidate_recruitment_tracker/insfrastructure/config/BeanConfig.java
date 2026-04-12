package lk.practice.candidate_recruitment_tracker.insfrastructure.config;

import lk.practice.candidate_recruitment_tracker.domain.repository.CandidateRepository;
import lk.practice.candidate_recruitment_tracker.usecase.CandidateUseCase;
import lk.practice.candidate_recruitment_tracker.usecase.CandidateUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public CandidateUseCase candidateUseCase(CandidateRepository candidateRepository){
        return  new CandidateUseCaseImpl(candidateRepository);
    }
}