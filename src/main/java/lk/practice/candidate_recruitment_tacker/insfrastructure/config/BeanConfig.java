package lk.practice.candidate_recruitment_tacker.insfrastructure.config;

import lk.practice.candidate_recruitment_tacker.domain.repository.CandidateRepository;
import lk.practice.candidate_recruitment_tacker.usecase.CandidateUseCase;
import lk.practice.candidate_recruitment_tacker.usecase.CandidateUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public CandidateUseCase candidateUseCase(CandidateRepository candidateRepository){
        return  new CandidateUseCaseImpl(candidateRepository);
    }
}