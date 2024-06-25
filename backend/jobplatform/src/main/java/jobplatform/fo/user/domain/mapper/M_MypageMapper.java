package jobplatform.fo.user.domain.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jobplatform.fo.user.domain.vo.M_JobPosting_pp;

@Mapper
public interface M_MypageMapper {
	
//	회원 정보 중 이름, 전화번호, 이메일, 포지션 제안 수락여부
//	mbr_name, mbr_mp, mbr_eml_adrs, pstn_prpsl_accept_yn
	public Map<String, Object> getMbrInfo(int mbr_sq);
	
//	이력서 중 대표 이력서 이력서 순번, 이력서 제목, 등록일시, 최종 수정일시, 이미지
//	rsm_sq, rsm_tl, insrt_dtm, updt_dtm, rsm_img_file_rul
	public Map<String, Object> getRprsntvRsmInfo(int mbr_sq);

//	대표 이력서 -> 지원 테이블에서 각 상태별 데이터 개수 가지고 오기(미열람:401, 열람:402, 면접대기:405, 면접완료:406, 합격:407, 불합격:408)
	public Map<String, Integer> getEachCndtnApplyCount(int rsm_sq);

//	등록한 이력서 개수(rsm_cnt), 스크랩한 공고 개수(scrap_cnt), 포지션 제안 받은 개수(대가 상태(301)인 제안)(prpsl_cnt), (추후 AI추천 공고 개수)
	public Map<String, Integer> getMyState(@Param("mbr_sq")int mbr_sq,@Param("rsm_sq") int rsm_sq);
	
	
//	월별 스크랩한 공고 마감일자 : S`
//	월별 면접일자 면접대기(405) I
//	웗별 제안 받은 포지션 대기 상태일 때 마감 일자 PP
//	월별 지원 공고 데이터 AC
	public List<Map<String, Object>> getToMakeCalendarData(@Param("mbr_sq")int mbr_sq,@Param("rsm_sq") int rsm_sq,@Param("month") int month);
	
	
//	포지션 제안 수락 여부 Check
	public String getPstnPrpslAcceptYN(int mbr_sq);

// 	포지션 제안 수락 여부 변경
	public int updatePstnPrpslAcceptYN(@Param("mbr_sq") int mbr_sq, @Param("pstn_prpsl_accept_yn") char pstn_prpsl_accept_yn);

//	 지역 정보 가져오기
	public List<Map<String, Object>> getAreaCodes();

// 	직업 정보 가져오기
	public List<Map<String, Object>> getJobCodes();

// 	설정했던 지역 검색
	public List<Integer> getSelectedAreas(int mbr_sq);

// 	선택했던 지역 초기화
	public int resetSelectedAreas(int mbr_sq);

// 	선택한 지역 INSERT
	public int insertSelectAreas(@Param("mbr_sq") int mbr_sq, @Param("areaList") List<Integer> areaList);

// 	설정했던 직업 검색
	public List<Integer> getSelectedJobs(int mbr_sq);

// 	선택했던 직업 초기화
	public int resetSelectedJobs(int mbr_sq);

// 	선택한 직업 INSERT
	public int insertSelectJobs(@Param("mbr_sq") int mbr_sq, @Param("jobList") List<Integer> jobList);

//	포지션 제안 받은 공고 리스트 출력
	public List<M_JobPosting_pp> getPPJopPostingData(@Param("rsm_sq") int rsm_sq);



// 	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@tools@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//	대표이력서 rsm_sq 구하는 메서드
	public int getRRsm_sq(int mbr_sq);
//	지역 코드 -> 지역 이름
	public List<String> areaCodeToName(@Param("areas") List<Integer> areas);
//	직업 코드 -> 직업 이름
	public List<String> jobCodeToName(@Param("jobs") List<Integer> jobs);
//	스킬 코드 -> 스킬 이름
	public List<String> skillCodeToName(@Param("skills") List<Integer> skills);

}
