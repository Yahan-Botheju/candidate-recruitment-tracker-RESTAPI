package lk.practice.candidate_recruitment_tacker.usecase;

import lk.practice.candidate_recruitment_tacker.domain.model.Candidate;
import lk.practice.candidate_recruitment_tacker.domain.model.CandidateStatus;
import lk.practice.candidate_recruitment_tacker.domain.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CandidateUseCaseImpl implements CandidateUseCase{

    //inject domain repo for initiate class as bean
    private final CandidateRepository candidateRepository;

    //get all candidates
    @Override
    public List<Candidate> getAllCandidates(int page, int size){

        //get all candidate from db
        List<Candidate> candidate = candidateRepository.getAllCandidates(page, size);

        //set each candidate to score using score calculate method
        return candidate.stream()
                .map(recruiters -> {
                    double score = recruitmentScoreCalculate(recruiters);
                    recruiters.setRecruitmentScore(score);
                    return recruiters;
                }).toList();

    }

    //save candidate
    @Override
    public void saveCandidate(Candidate candidate){
        //set status applied for fresh candidate
        candidate.setStatus(CandidateStatus.APPLIED);

        candidateRepository.saveCandidate(candidate);
    }

    //update candidate
    @Override
    public void updateCandidate(Long id, Candidate candidate){
        //set value to domain repo
        candidateRepository.updateCandidate(id, candidate);
    }

    //delete candidate
    @Override
    public void deleteCandidate(Long id){
        //soft delete by id
        candidateRepository.deleteCandidate(id);
    }

    //create usecase own method for calculate recruitment score
    private double recruitmentScoreCalculate(Candidate candidate){
        //calculate experience points
        double experiencePoints = candidate.getExperience() * 10;
        //calculate salary points
        double salaryPoints = candidate.getExpectedSalary() > 500000
                ?  (int)((candidate.getExpectedSalary() - 500000) / 100000) * 5
                : 0;

        return  experiencePoints - salaryPoints;

    }
}