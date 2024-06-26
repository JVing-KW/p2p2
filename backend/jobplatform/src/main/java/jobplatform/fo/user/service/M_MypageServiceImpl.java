package jobplatform.fo.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jobplatform.fo.user.domain.mapper.M_MypageMapper;
import jobplatform.fo.user.domain.vo.M_JobPosting_pp;


@Service
public class M_MypageServiceImpl implements M_MypageService{

	@Autowired
    private M_MypageMapper mypageMapper;
	
    //마이페이지 매인화면에 필요한 전체 데이터 가져오기
	@Override
	public Map<String, Object> getMyPageMainData(int mbr_sq, int month) {
		
		Map<String, Object> response = new HashMap<>();

        // 회원 정보
        Map<String, Object> mbrInfo = mypageMapper.getMbrInfo(mbr_sq);
        response.put("mbrInfo", mbrInfo);

        // 대표 이력서 정보
        Map<String, Object> rsmInfo = mypageMapper.getRprsntvRsmInfo(mbr_sq);
        response.put("rsmInfo", rsmInfo);
        
        int rsm_sq = (int)rsmInfo.get("rsm_sq");

        // 각 상태별 지원 개수
        Map<String, Integer> EachCndtnApplyCount = mypageMapper.getEachCndtnApplyCount(rsm_sq);
        response.put("EachCndtnApplyCount", EachCndtnApplyCount);

        // 등록한 이력서, 스크랩한 공고, 포지션 제안 받은 개수
        Map<String, Integer> myState = mypageMapper.getMyState(mbr_sq, rsm_sq);
        response.put("myState", myState);

        // 월별 캘린더 데이터
        List<Map<String, Object>> calendarData = mypageMapper.getToMakeCalendarData(mbr_sq,rsm_sq, month);
        response.put("calendarData", calendarData);
		
		return response;
	}

    //마이페이지 메인 화면 캘린더 데이터(월 이동시)
    @Override
    public List<Map<String, Object>> getToMakeCalendarData(int mbr_sq, int rsm_sq, int month) {
        return mypageMapper.getToMakeCalendarData(mbr_sq,rsm_sq, month);
    }


//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    //포지션 제안 받기 페이지 데이터
    @Override
    public Map<String, Object> getPosionProposalData(int mbr_sq) {

        Map<String, Object> response = new HashMap<>();

        response.put("ppAcceptYN", mypageMapper.getPstnPrpslAcceptYN(mbr_sq));
        response.put("rsmInfo", mypageMapper.getRprsntvRsmInfo(mbr_sq));
        response.put("selectedAreas", mypageMapper.getSelectedAreas(mbr_sq));
        response.put("selectedJobs", mypageMapper.getSelectedJobs(mbr_sq));

        response.put("areaList", mypageMapper.getAreaCodes());
        response.put("jobList", mypageMapper.getJobCodes());


        return response;
    }

    //포지션 제안 받기 수락 여부 변경
    @Override
    public int updatePstnPrpslAcceptYN(int mbr_sq, char pstn_prpsl_accept_yn) {
        return mypageMapper.updatePstnPrpslAcceptYN(mbr_sq, pstn_prpsl_accept_yn);
    }

    //포지션 제안 받을 때, 지역, 직업 선택
    @Override
    public int insertSelectAreasAndJobs(int mbr_sq, List<Integer> areaList, List<Integer> jobList) {
        int result = 0;

        mypageMapper.resetSelectedAreas(mbr_sq);
        mypageMapper.resetSelectedJobs(mbr_sq);
        result += mypageMapper.insertSelectAreas(mbr_sq, areaList);
        result += mypageMapper.insertSelectJobs(mbr_sq, jobList);

        return result;
    }


    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    //제안 받은 포지션 공고 리스트 출력
    //검색에 사용하기 위해 vo에 추가 변수(forSearch)
    @Override
    public List<M_JobPosting_pp> getPPJopPostingData(int mbr_sq){
        int rsm_sq = mypageMapper.getRRsm_sq(mbr_sq);
        List<M_JobPosting_pp> result = mypageMapper.getPPJopPostingData(rsm_sq);
        for(M_JobPosting_pp temp : result){
            int jbp_sq = temp.getJbp_sq();
            temp.setAreas(mypageMapper.areaCodeToName(mypageMapper.getAreasOfJobPost(jbp_sq)));
            temp.setJobs(mypageMapper.jobCodeToName(mypageMapper.getJobsOfJobPost(jbp_sq)));
            temp.setSkills(mypageMapper.skillCodeToName(mypageMapper.getSkillsOfJobPost(jbp_sq)));
            temp.setForSeacrch(temp.toString());
            System.out.println(temp);
        }

        return result;
    };


    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@



}
