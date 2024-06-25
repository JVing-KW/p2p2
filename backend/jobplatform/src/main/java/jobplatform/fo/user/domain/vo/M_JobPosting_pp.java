package jobplatform.fo.user.domain.vo;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
public class M_JobPosting_pp {
    private int jbp_sq;
    private int entrprs_sq;
    private String jbp_tl;
    private String jbp_cntnt;
    private String cr;
    private String edctn;
    private Timestamp regstr_dln_dtm;
    private int pstn_prpsl_sq;
    private Timestamp prpsl_dtm;
    private int pstn_prpsl_cndtn;
    private String entrprs_name;
    private List<Integer> area_sq;
    private List<Integer> job_sq;
    private List<Integer> skl_cd_sq;
    private String forSeacrch;

    @Override
    public String toString(){
        return jbp_tl+jbp_cntnt+cr+edctn+entrprs_name+area_sq+job_sq+skl_cd_sq;
    }

}
