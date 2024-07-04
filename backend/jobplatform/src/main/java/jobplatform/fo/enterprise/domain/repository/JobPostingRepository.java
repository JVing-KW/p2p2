package jobplatform.fo.enterprise.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jobplatform.fo.enterprise.domain.entity.JobPostingEntity;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPostingEntity, Integer>{
	

	// 공고 리스트 조회 및 정렬
    @Query("SELECT j FROM JobPostingEntity j ORDER BY "
            + "CASE WHEN :sortBy = 'hits' THEN j.hits END DESC, "
            + "CASE WHEN :sortBy = 'regstrStrtDtm' THEN j.regstrStrtDtm END DESC")
       List<JobPostingEntity> findAllJobPostings(@Param("sortBy") String sortBy);
	
    // 조회수 증가
	@Modifying
    @Query("UPDATE JobPostingEntity j SET j.hits = j.hits + 1 WHERE j.jbpSq = :jbpSq")
    int increaseHits(@Param("jbpSq") int jbpSq);
    
    // 검색 메소드
    List<JobPostingEntity> findByJbpTlContainingOrJbpCntntContaining(String jbpTl, String jbpCntnt);
	List<JobPostingEntity> findByJbpTlContaining(String jpbTl);
	List<JobPostingEntity> findByJbpCntntContaining(String jbpCntnt);


}
