package jobplatform.fo.enterprise.domain.entity;

import java.time.LocalDateTime;

import jobplatform.common.Role;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "P2_TBL_ENTERPRISE_MEMBER_M")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnterMemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entrprs_sq", nullable = false)
    private long entrprsSq; // 기업_순번

    @Column(name = "scl_ctgry_cd", nullable = true)
    private String sclctGryCd; // 소셜_유형_코드

    @Column(name = "gndr_ctgry_cd", nullable = false)
    private String gndrCtryCd; // 성별_유형_코드

    @Column(name = "entrprs_id", nullable = false)
    private String entrprsId; // 기업_아이디

    @Column(name = "entrprs_pswrd", nullable = false)
    private String entrprsPswrd; // 기업_비밀번호

    @Column(name = "entrprs_pic_name", nullable = false)
    private String entrprsPicName; // 기업담당자_이름

    @Column(name = "entrprs_pic_eml", nullable = false)
    private String entrprsPicEml; // 기업담당자_이메일_주소

    @Column(name = "entrprs_pic_mp", nullable = false)
    private String entrprsPicMp; // 기업담당자_휴대폰

    @Column(name = "entrprs_name", nullable = true)
    private String entrprsName; // 기업이름

    @Column(name = "entrprs_adrs", nullable = true)
    private String entrprsAdrs; // 기업_주소

    @Column(name = "entrprs_ceo_name", nullable = true)
    private String entrprsCeoName; // 기업 대표자이름

    @Column(name = "cmn_rgtrrtn_nmbr", nullable = true)
    private String cmnRgtrrtnNmbr; // 사업자등록번호

    @Column(name = "entrprs_hp", nullable = true)
    private String entrprsHp; // 기업 홈페이지

    @Column(name = "cmn_rgtrtn_crtfctn_nmbr", nullable = true)
    private Long cmnRgtrtnCrtfctnNmbr; // 사업자등록원증명번호

    @Column(name = "logo_file_url", nullable = true)
    private String logoFileUrl; // 기업_파일_url

    @Column(name = "entrprs_eml_rcv_yn", nullable = false)
    private String entrprsEmlRcvYn; // 기업담당자_이메일_수신_여부

    @Column(name = "entrprs_prvcy_trms_yn", nullable = false)
    private String entrprsPrvcyTrmsYn; // 회원 개인정보 약관 동의 여부

    @Column(name = "insrt_entrprs_sq", nullable = true)
    private int insrtEntrprsSq; // 등록_기업_순번

    @CreatedDate
    @Column(name = "insrt_entrprs_dtm", nullable = true)
    private LocalDateTime insertEntrprsDtm; // 등록_일시

    @Column(name = "updt_entrprs_sq", nullable = true)
    private long updtEntrprsSq; // 수정_기업_순번

    @LastModifiedDate
    @Column(name = "updt_entrpts_dtm", nullable = true)
    private LocalDateTime updtEntrptsDtm; // 수정_일시

    @Column(name = "dlt_yn", nullable = true)
    private String dltYn; // 삭제 여부

    @Column(name = "use_yn", nullable = true)
    private String useYn; // 사용 여부

    @Column(name = "del", nullable = true)
    private String del; // 삭제 여부

    @Column(name = "social", nullable = true)
    private String social; // 소셜 여부

    @Column(name ="role",nullable =true)
    private Role role;
}
