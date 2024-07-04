package jobplatform.fo.enterprise.service;

import jobplatform.fo.enterprise.domain.dto.EnterLoginDTO;
import jobplatform.fo.enterprise.domain.dto.EnterRegisterDTO;

public interface EnterMemberService {

    public void insert(EnterRegisterDTO enterMemberDTO);
    
    
    public Long login(EnterLoginDTO enterLoginDTO);

    public boolean selectId(String id)
;}
