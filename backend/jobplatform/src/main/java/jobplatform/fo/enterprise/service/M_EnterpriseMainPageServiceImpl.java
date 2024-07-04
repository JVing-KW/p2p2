package jobplatform.fo.enterprise.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jobplatform.fo.enterprise.domain.mapper.M_EnterpriseMainPageMapper;

@Service
public class M_EnterpriseMainPageServiceImpl implements M_EnterpriseMainPageService{

    @Autowired
    private M_EnterpriseMainPageMapper enterpriseMainPageMapper;

    @Override
    public Map<String, Object> getMyJobPostSimpleData(int entrprs_sq) {
        Map<String, Object> result = new HashMap<>();
        result.put("myJobPostSimpleData", enterpriseMainPageMapper.getMyJobPostSimpleData(entrprs_sq));
        return result;
    }
    
}
