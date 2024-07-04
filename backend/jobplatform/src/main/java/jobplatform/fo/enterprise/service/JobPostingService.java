package jobplatform.fo.enterprise.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jobplatform.fo.enterprise.domain.dto.JobPostingDTO;
import jobplatform.fo.enterprise.domain.entity.JobPostingEntity;

@Service
public interface JobPostingService {

	public List<JobPostingEntity> jobPostingList(String sortBy);

	public int insertJobPosting(JobPostingEntity jpe);

	public JobPostingDTO jobPostingDetail(int jbpSq);

	public int increaseHits(int jbpSq);

	public void updateJobPosting(JobPostingEntity jpe);

	public void deleteJobPosting(int jbpSq);


	public List<JobPostingEntity> searchJobPostings(String searchTerm, String searchField);



	


	
}
