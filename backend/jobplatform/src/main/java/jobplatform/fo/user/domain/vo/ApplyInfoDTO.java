package jobplatform.fo.user.domain.vo;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Data
public class ApplyInfoDTO {
    private Long apySq;         // 지원순번
    private Long rsmSq;         // 이력서번호
    private Long mbrSq;         // 회원번호
    private String rsmTl;       // 이력서제목
    private String jbpTl;       // 공고제목
    private String apyDtm;      // 지원일시
    private String jbpCndtn;    // 공고상태
    private String entrprsName; // 기업이름
    private String codeName;    // 지원상태
    private Long applyCount; // 지원자 수
    
}
