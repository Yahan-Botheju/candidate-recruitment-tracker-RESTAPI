package lk.practice.candidate_recruitment_tracker.usecase;

import lk.practice.candidate_recruitment_tracker.domain.model.Candidate;
import lk.practice.candidate_recruitment_tracker.domain.model.CandidateStatus;
import lk.practice.candidate_recruitment_tracker.domain.repository.CandidateRepository;
import lk.practice.candidate_recruitment_tracker.usecase.records.CreateNewCandidateCommand;
import lk.practice.candidate_recruitment_tracker.usecase.records.CreateNewCandidateResult;
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
    public CreateNewCandidateResult saveCandidate(CreateNewCandidateCommand createNewCandidateCommand) {

        if(candidateRepository.getCandidateByEmail(createNewCandidateCommand.email()).isPresent()){
            throw new IllegalStateException("User already exists, cannot proceed the request!!");
        }
        //create new candidate through domain
        Candidate newCandidate = Candidate.createCandidate(
                createNewCandidateCommand.fullName(),
                createNewCandidateCommand.email(),
                createNewCandidateCommand.experience(),
                createNewCandidateCommand.appliedRole(),
                createNewCandidateCommand.expectedSalary(),
                CandidateStatus.APPLIED,
                0.0
        );
        //calculate recruitment score
        newCandidate.recruitmentScoreCalculate();

        Candidate savedCandidates = candidateRepository.saveCandidate(newCandidate);

        return new CreateNewCandidateResult(
                savedCandidates.getId().toString(),
                savedCandidates.getFullName(),
                savedCandidates.getEmail(),
                savedCandidates.getExperience(),
                savedCandidates.getAppliedRole(),
                savedCandidates.getExpectedSalary(),
                savedCandidates.getStatus().toString()
        );
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
        candidateRepository.updateCandidate(id, candidate);
    }

}