package yxinfo.core.service.ou.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import yxinfo.core.service.ou.EnterpriseService;
import yxinfo.core.service.ou.dao.mapper.EnterpriseManagerMapper;
import yxinfo.core.service.ou.dao.model.EnterpriseManager;
import yxinfo.core.service.ou.dao.model.EnterpriseManagerExample;

import javax.annotation.Resource;

/**
 * Created by dy on 2017/9/20.
 */
@Component
@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    @Resource
    private EnterpriseManagerMapper enterpriseManagerMapper;

    public void addEnterpriseManager( Integer memberId, Integer enterpriseId ) {
        EnterpriseManager enterpriseManager = new EnterpriseManager();
        enterpriseManager.setMemberId( memberId );
        enterpriseManager.setEnterpriseId( enterpriseId );
        enterpriseManagerMapper.insert( enterpriseManager );
    }

    public void deleteEnterpriseManager( Integer memberId ) {
        EnterpriseManagerExample example = new EnterpriseManagerExample();
        example.createCriteria().andMemberIdEqualTo( memberId );
        enterpriseManagerMapper.deleteByExample( example );
    }
}
