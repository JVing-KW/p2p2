package jobplatform.fo.user.domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jobplatform.fo.user.domain.vo.ApplyInfoDTO;

@Mapper
public interface MypageMapper {
	
//	Map<String, Object> getJoinCondition(int Id);
	
	Integer getid(String id);
	
	List<ApplyInfoDTO> getApplyInfo(int mbrSq); // 지원내역

	Integer getTotalApplyCount(Integer mbrSq); // 지원완료
	
	void delAppy(Integer apySq); // 지원취소
}
