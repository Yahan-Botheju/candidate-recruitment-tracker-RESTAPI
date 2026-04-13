package lk.practice.candidate_recruitment_tracker.usecase;

import lk.practice.candidate_recruitment_tracker.domain.model.Candidate;
import lk.practice.candidate_recruitment_tracker.domain.model.CandidateStatus;
import lk.practice.candidate_recruitment_tracker.domain.repository.CandidateRepository;
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

    //create isAdmin function, ROLE != ADMIN  ,SALARY == 0
    @Override
    public Candidate isAdmin(Candidate candidate, String role){
        if(!"ADMIN".equalsIgnoreCase(role)){
            candidate.setExpectedSalary(0.0);
        }
        return candidate;
    }

    //update candidate status method
    @Override
    public void updateCandidateStatus(Long id, CandidateStatus candidateStatus){
        //get related candidate by id through repo method
        Candidate candidate = candidateRepository.getCandidate(id);

        //check taken candidate status == HIRED || REJECTED
        if(candidate.getStatus().equals(CandidateStatus.HIRED) ||
           candidate.getStatus().equals(CandidateStatus.REJECTED)
        ){
            throw new RuntimeException("Candidate status cannot be edited");
        }

        //set new status to candidate
        candidate.setStatus(candidateStatus);

        //save new status of candidate using save method(CRUD)
        candidateRepository.saveCandidate(candidate);
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