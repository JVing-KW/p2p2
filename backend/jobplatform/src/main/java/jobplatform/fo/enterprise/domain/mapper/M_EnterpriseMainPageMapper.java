package jobplatform.fo.enterprise.domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jobplatform.fo.enterprise.domain.vo.JobPostingVO;
import jobplatform.fo.enterprise.domain.vo.MyJobPostSimpleDataVO;
import jobplatform.fo.enterprise.domain.vo.ResumeVO;

@Mapper
public interface M_EnterpriseMainPageMapper {

    //기업 매인 페이지 공고 + 공고별 지원 현황 데이터
    public List<MyJobPostSimpleDataVO> getMyJobPostSimpleData(int entrprs_sq);

    //공고 번호로 공고 정보 가지고 오기
    public JobPostingVO getJobPostData(int jbp_sq);

    //공고 번호로 필터링(제안여부, 지역, 직업, 대표)된 이력서리스트 가져오기
    public List<ResumeVO> getMatchResumes(int jbp_sq);
}
