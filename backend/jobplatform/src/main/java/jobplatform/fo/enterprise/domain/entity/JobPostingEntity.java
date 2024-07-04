package jobplatform.fo.enterprise.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "P2_TBL_JOB_POSTING_M")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPostingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jbp_sq", nullable = false)
    private int jbpSq;

//    @ManyToOne
//    @JoinColumn(name = "entrprs_sq", referencedColumnName = "entrprs_sq", nullable = false)
//    private EnterMemberEntity enterpriseMember;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entrprs_sq", referencedColumnName = "entrprs_sq", nullable = false)
    private EnterMemberEntity enterpriseMember;

    
    
    @Column(name = "jbp_tl", nullable = false, length = 30)
    private String jbpTl = "";

    @Column(name = "jbp_cntnt", nullable = false, length = 500)
    private String jbpCntnt = "";

    @Column(name = "hits", nullable = false)
    private int hits = 0;

    @Column(name = "cr", nullable = false, length = 20)
    private String cr = "";

    @Column(name = "skl_name", nullable = false, length = 30)
    private String sklName = "";

    @Column(name = "job_name", nullable = false, length = 20)
    private String jobName = "";

    @Column(name = "edctn", nullable = false, length = 10)
    private String edctn = "";

    @Column(name = "work_area", nullable = false, length = 50)
    private String workArea = "";

    @Column(name = "work_form", nullable = false, length = 10)
    private String workForm = "";

    @Column(name = "slry", nullable = false, length = 10)
    private String slry = "";

    @Column(name = "work_hour", nullable = false, length = 10)
    private String workHour = "";

    @Column(name = "regstr_strt_dtm", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate regstrStrtDtm;

    @Column(name = "regstr_dln_dtm", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate regstrDlnDtm;

    @Column(name = "pic_name", nullable = false, length = 10)
    private String picName = "";

    @Column(name = "pic_mp", nullable = false, length = 20)
    private String picMp = "";

    @Column(name = "pic_eml", nullable = false, length = 50)
    private String picEml = "";

    @Column(name = "jbp_end_yn", nullable = false, length = 1)
    private String jbpEndYn = "N";

    @Column(name = "jbp_cndtn", nullable = false, length = 10)
    private String jbpCndtn = "";

    @Column(name = "insrt_mbr_sq", nullable = false)
    private int insrtMbrSq = 0;

    @Column(name = "insrt_dtm", nullable = false)
    private LocalDateTime insrtDtm = LocalDateTime.now();

    @Column(name = "updt_mbr_sq")
    private Integer updtMbrSq;

    @Column(name = "updt_dtm", nullable = true)
    private LocalDateTime updtDtm;

    public enum YesNo {
        Y, N
    }

    @Column(name = "dlt_yn", nullable = false)
    @Enumerated(EnumType.STRING)
    private YesNo dltYn = YesNo.N;

    @Column(name = "use_yn", nullable = false)
    @Enumerated(EnumType.STRING)
    private YesNo useYn = YesNo.Y;
}
