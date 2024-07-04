package jobplatform.fo.user.service;

import java.util.List;
import java.util.Map;

import jobplatform.fo.user.domain.vo.ApplyInfoDTO;

public interface MypageService {
//	Map<String, Object> getJoinCondition(int id);
	
	Integer getId(String id); // 회원순번
	
	List<ApplyInfoDTO> getApplyInfo(int mbrSq); // 지원정보
	
	Integer getTotalApplyCount (Integer mbrSq); // 지원완료
	
	void delAppy(Integer apySq); // 지원취소
}
