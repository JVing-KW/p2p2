package jobplatform.fo.enterprise.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jobplatform.fo.enterprise.domain.dto.JobPostingDTO;
import jobplatform.fo.enterprise.domain.entity.JobPostingEntity;
import jobplatform.fo.enterprise.service.JobPostingService;


@RestController
public class JobPostingController {
	
	@Autowired  
	private JobPostingService jobPostingService;
	
	// 공고 리스트 조회 메소드
    @GetMapping("/board/list/jobPosting")
    public List<JobPostingEntity> jobPostingList(@RequestParam(value = "sortBy", defaultValue = "regstrStrtDtm") String sortBy) {
        return jobPostingService.jobPostingList(sortBy);
    }
	
	// 공고 등록 메소드
	@PostMapping("/board/jobPostingInsert")
	public ResponseEntity<Map<String, Object>> insertJobPosting(@RequestBody JobPostingEntity jpe) {
	    int insertedJbpSq = jobPostingService.insertJobPosting(jpe);
	    
	    // 상세 페이지로 이동
	    String detailUrl = "/board/detail/jobPosting/" + insertedJbpSq;
	    
	  
	    Map<String, Object> response = new HashMap<>();
	    response.put("detailUrl", detailUrl);
	    response.put("jbpSq", insertedJbpSq);
	    
	    return ResponseEntity.ok(response);
	}
	
	// 공고 상세 조회 메소드
	@GetMapping("/board/detail/jobPosting/{jbpSq}")
	public JobPostingDTO JobPostingDetail(@PathVariable int jbpSq) {
		JobPostingDTO jpe = jobPostingService.jobPostingDetail(jbpSq);
		
		// 조회수 증가
		jobPostingService.increaseHits(jbpSq);
		
		return jpe;
	}
	
	// 공고 수정 메소드
	@PostMapping("/board/jobPostingUpdate/{jbpSq}")
	public void updateJobPosting(@PathVariable int jbpSq , @RequestBody JobPostingEntity jpe) {
		jobPostingService.updateJobPosting(jpe);
	}
	
	
	// 공고 삭제 메소드
	@DeleteMapping("/board/jobPostingDelete/{jbpSq}")
	public void deleteJobPosting(@PathVariable int jbpSq) {
		jobPostingService.deleteJobPosting(jbpSq);
	}
	
	// 공고 검색 메소드
    @GetMapping("/board/search")
    public List<JobPostingEntity> searchJobPostings(
            @RequestParam(value = "searchTerm", required = false) String searchTerm,
            @RequestParam(value = "searchField", defaultValue = "jbpTl_jbpCntnt") String searchField) {
        	System.out.println("검색 잘 되니"+searchTerm);
        	System.out.println("검색 잘 되니"+searchField);
        return jobPostingService.searchJobPostings(searchTerm, searchField);
    }
    

	
}
