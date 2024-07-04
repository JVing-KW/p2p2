package jobplatform.fo.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jobplatform.fo.user.domain.mapper.MypageMapper;
import jobplatform.fo.user.domain.vo.ApplyInfoDTO;

@Service
public class MypageServieImpl implements MypageService{
	
	@Autowired
	private MypageMapper mypagemapper;
	
//	public Map<String, Object> getJoinCondition(int id) {
//		return mypagemapper.getJoinCondition(id);
//	}

	@Override
	public Integer getId(String id) {
		// TODO Auto-generated method stub
		return mypagemapper.getid(id);
	}

	@Override
	public List<ApplyInfoDTO> getApplyInfo(int mbrSq) {
		// TODO Auto-generated method stub
		return mypagemapper.getApplyInfo(mbrSq);
	}

	@Override
	public Integer getTotalApplyCount(Integer mbrSq) {
		// TODO Auto-generated method stub
		return mypagemapper.getTotalApplyCount(mbrSq);
	}

	@Override //지원취소
	public void delAppy(Integer apySq) {
		// TODO Auto-generated method stub
		mypagemapper.delAppy(apySq);
	}
}
