package jobplatform.fo.enterprise.service;

import java.util.Map;

public interface M_EnterpriseMainPageService {

    public Map<String, Object> getMyJobPostSimpleData(int entrprs_sq);


    //공고별 이력서 매칭시키기!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //일단 DB에서 대표이력서 기준 제안 했었는지 체크하고, 지역, 직업 겹치는거 있는 이력서 데이터 가지고옴
    //서비스에서 공고 내용이랑 이력서의 내용 비교
    //비교 대상 : 최종학력, 스킬 목록
    //공고 기준 학력, 스킬 개수 합을 1로 잡고 매칭 되는 항목마다 1/n 점수 부여, 만점 1
    //매칭 점수 토대로 상위 이력서부터 출력
}
