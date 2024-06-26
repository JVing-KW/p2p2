package jobplatform.fo.enterprise.domain.vo;

import java.util.List;

import lombok.Data;

@Data
public class ResumeVO {
    private int rsm_sq;
    private int mbr_sq;
    private String rsm_img_file_url;
    private int rsm_fnl_edctn_cd;      //비교대상
    private int rsm_es;                //나중에 비교할 수도 있음
    private String rsm_tl;
    private String rsm_eml;
    private String updt_dtm;
    private List<String> crtfct_name;
    private List<Integer> skl_cd_sq;   //비교대상

    private int matchingPoint;
}
