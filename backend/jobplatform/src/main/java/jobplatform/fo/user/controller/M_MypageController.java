package jobplatform.fo.user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jobplatform.fo.user.domain.vo.M_JobPosting_pp;
import jobplatform.fo.user.service.M_MypageService;




@RestController
@RequestMapping("/mypage")
public class M_MypageController {
	
	@Autowired
	private M_MypageService myPageService;
	
	//마이페이지 메인 화면 데이터
	//jwt 구현 전 까지 클라에서 mbr_sq 같이 넘겨주는 걸로!
	@GetMapping("/main")
	public ResponseEntity<Map<String, Object>> getMainData(@RequestParam("mbr_sq") int mbr_sq, @RequestParam("month") int month) {
		return ResponseEntity.ok(myPageService.getMyPageMainData(mbr_sq, month));
	}

	//마이페이지 메인 화면 달력 데이터
	//달력 월별 데이터 제공 *mbr_sq, rsm_sq는 어떻게 처리할지 미정
	@GetMapping("/calendar")
	public ResponseEntity<List<Map<String, Object>>> getCalendarData(@RequestParam("month") int month) {
		return ResponseEntity.ok(myPageService.getToMakeCalendarData(1,1, month));
	}

	//마이페이지 포지션 제안 받기 화면 데이터(대표이력서, 제안 수락 여부, 선택했던 지역&직업 + 지역&직업 데이터)
	@GetMapping("/ppAcception")
	public ResponseEntity<Map<String, Object>> getPosionProposalData(@RequestParam("mbr_sq") int mbr_sq) {
		return ResponseEntity.ok(myPageService.getPosionProposalData(mbr_sq));
	}

	//포지션 제안받기 상태 변경
	@PutMapping("/ppAcception/{ppyn}")
	public int putMethodName(@RequestParam("mbr_sq") int mbr_sq, @PathVariable char ppyn) {
		return myPageService.updatePstnPrpslAcceptYN(mbr_sq, ppyn);
	}

	//포지션 제안 받기 설정 후 원하는 지역, 직종 선택 -> 기존 데이터 삭제 후 재입력
	@PostMapping("/ppAcception")
	@SuppressWarnings("unchecked")
	public int postMethodName(@RequestParam("mbr_sq") int mbr_sq, @RequestBody Map<String, Object> areaAndJobLists) {
		List<Integer> areaList = (List<Integer>)areaAndJobLists.get("areaList");
		List<Integer> jobList = (List<Integer>)areaAndJobLists.get("jobList");
		return myPageService.insertSelectAreasAndJobs(mbr_sq, areaList, jobList);
	}

	//제안 받은 포지션 공고 리스트
	@GetMapping("/ppJobPost")
	public List<M_JobPosting_pp> getMethodName(@RequestParam("mbr_sq") int mbr_sq) {
		return myPageService.getPPJopPostingData(mbr_sq);
	}
	
	
	
	
	
}
