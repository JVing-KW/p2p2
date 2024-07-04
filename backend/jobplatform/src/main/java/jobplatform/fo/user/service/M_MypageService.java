package jobplatform.fo.user.service;

import java.util.List;
import java.util.Map;

import jobplatform.fo.user.domain.vo.M_JobPosting_pp;

public interface M_MypageService {

	//마이페이지 매인화면에 필요한 전체 데이터 가져오기
	public Map<String, Object> getMyPageMainData(int mbr_sq, int month);

	//마이페이지 메인 화면 캘린더 데이터(월 이동시)
	public List<Map<String, Object>> getToMakeCalendarData(int mbr_sq, int rsm_sq, int month);

	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	//포지션 제안 받기 페이지 데이터
	public Map<String, Object> getPosionProposalData(int mbr_sq);

	//포지션 제안 받기 수락 여부 변경
	public int updatePstnPrpslAcceptYN(int mbr_sq, char pstn_prpsl_accept_yn);

	//포지션 제안 받을 때, 지역, 직업 선택
	public int insertSelectAreasAndJobs(int mbr_sq, List<Integer> areaList, List<Integer> jobList);

	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	//제안 받은 포지션 공고
	public List<M_JobPosting_pp> getPPJopPostingData(int mbr_sq);

	//@@예정@@포지션 제안 수락했을 때 -> 같은 공고로 지원한 내역 있는지 확인 + 지원 프로세스 진행 + 포지션 제안 상태 변경 
	//@@예정@@포지션 제안 거절했을 때 -> 포지션 제안 상태 변경

}
