package jobplatform.fo.enterprise.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jobplatform.fo.enterprise.domain.entity.JobPostingEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPostingDTO {

    private int jbpSq;
    private String jbpTl;
    private String jbpCntnt;
    private int hits;
    private String cr;
    private String sklName;
    private String jobName;
    private String edctn;
    private String workArea;
    private String workForm;
    private String slry;
    private String workHour;
    private LocalDate regstrStrtDtm;
    private LocalDate regstrDlnDtm;
    private String picName;
    private String picMp;
    private String picEml;
    private String jbpEndYn;
    private String jbpCndtn;
    private int insrtMbrSq;
    private LocalDateTime insrtDtm;
    private Integer updtMbrSq;
    private LocalDateTime updtDtm;
    private String dltYn;
    private String useYn;
    private String enterpriseName; // 추가된 기업 이름 필드

    public static JobPostingDTO from(JobPostingEntity entity) {
        return JobPostingDTO.builder()
                .jbpSq(entity.getJbpSq())
                .jbpTl(entity.getJbpTl())
                .jbpCntnt(entity.getJbpCntnt())
                .hits(entity.getHits())
                .cr(entity.getCr())
                .sklName(entity.getSklName())
                .jobName(entity.getJobName())
                .edctn(entity.getEdctn())
                .workArea(entity.getWorkArea())
                .workForm(entity.getWorkForm())
                .slry(entity.getSlry())
                .workHour(entity.getWorkHour())
                .regstrStrtDtm(entity.getRegstrStrtDtm())
                .regstrDlnDtm(entity.getRegstrDlnDtm())
                .picName(entity.getPicName())
                .picMp(entity.getPicMp())
                .picEml(entity.getPicEml())
                .jbpEndYn(entity.getJbpEndYn())
                .jbpCndtn(entity.getJbpCndtn())
                .insrtMbrSq(entity.getInsrtMbrSq())
                .insrtDtm(entity.getInsrtDtm())
                .updtMbrSq(entity.getUpdtMbrSq())
                .updtDtm(entity.getUpdtDtm())
                .dltYn(entity.getDltYn().name()) // Enum 타입 처리
                .useYn(entity.getUseYn().name()) // Enum 타입 처리
                .enterpriseName(entity.getEnterpriseMember().getEntrprsName()) // 기업 이름 추가
                .build();
    }
}
