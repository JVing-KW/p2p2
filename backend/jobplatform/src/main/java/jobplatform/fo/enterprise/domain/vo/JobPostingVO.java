package jobplatform.fo.enterprise.domain.vo;

import java.util.List;

import lombok.Data;

@Data
public class JobPostingVO {
    private int jbp_sq;
    private int edctn;              //비교대상
    private int slry;
    List<Integer> skl_code_sq;      //비교대상
}
