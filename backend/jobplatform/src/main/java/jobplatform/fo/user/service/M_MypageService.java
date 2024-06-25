package jobplatform.fo.user.service;

import java.util.List;
import java.util.Map;

import jobplatform.fo.user.domain.vo.M_JobPosting_pp;

public interface M_MypageService {
	//마이페이지 매인화면에 필요한 전체 데이터 가져오기
	public Map<String, Object> getMyPageMainData(int mbr_sq, int month);

	//마이페이지 메인 화면 캘린더 데이터(월 이동시)
	public List<Map<String, Object>> getToMakeCalendarData(int mbr_sq, int rsm_sq, int month);

	//포지션 제안 받기 페이지 데이터
	public Map<String, Object> getPosionProposalData(int mbr_sq);

	// public List<Map<String, Object>> getAreaCodes();
	// public List<Map<String, Object>> getJobCodes();

	//포지션 제안 받기 수락 여부 변경
	public int updatePstnPrpslAcceptYN(int mbr_sq, char pstn_prpsl_accept_yn);

	//포지션 제안 받을 때, 지역, 직업 선택
	public int insertSelectAreasAndJobs(int mbr_sq, List<Integer> areaList, List<Integer> jobList);

	//제안 받은 포지션 공고
	public List<M_JobPosting_pp> getPPJopPostingData(int mbr_sq);



	//tools
	//회원 번호로 대표 공고 번호 받아오는 메소드
	//지역, 직업, 스킬 코드 코드 배열을 이름 배열로 바꿔주는 메소드
	public List<String> areaCodeToName(List<Integer> areas);
	public List<String> jobCodeToName(List<Integer> jobs);
	public List<String> skillCodeToName(List<Integer> skills);

}
