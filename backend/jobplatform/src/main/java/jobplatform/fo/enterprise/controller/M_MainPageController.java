package jobplatform.fo.enterprise.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jobplatform.fo.enterprise.service.M_EnterpriseMainPageService;


@RestController
@RequestMapping("/enterprise")
public class M_MainPageController {

    @Autowired
    private M_EnterpriseMainPageService enterpriseMainPageService;
    
    //로그인 전, 로그인 후, 인증 후 -> 로그인 전 + 로그인 후(미인증) 일 때는 프론트에서 이미 만들어진 페이지 출력
    //이 메서드는 인증까지 받은 회원이 기업 메인 화면 요청했을 때!
    @GetMapping("")
    public Map<String, Object> getEnterpriseMainPage(@RequestParam("entprs_sq") int entprs_sq){
        return enterpriseMainPageService.getMyJobPostSimpleData(entprs_sq);
    }

    //공고별 추천 인재 리스트 받기
    @GetMapping("/matchingResume/{jbp_sq}")
    public String getMatchingResumeByJbp_sq(@PathVariable int jbp_sq) {
        return new String();
    }
    
}
