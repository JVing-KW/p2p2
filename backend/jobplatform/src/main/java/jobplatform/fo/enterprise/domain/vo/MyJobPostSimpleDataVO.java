package jobplatform.fo.enterprise.domain.vo;

import java.util.List;

import lombok.Data;

@Data
public class MyJobPostSimpleDataVO {
    private int jbp_sq;
    private int entrprs_sq;
    private String jbp_tl;
    private String insrt_dtm;
    private int jbp_cndtn;
    private List<ApplyConditionCount> applicationStatusList;
}
