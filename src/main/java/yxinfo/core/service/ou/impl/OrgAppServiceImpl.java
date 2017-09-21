package yxinfo.core.service.ou.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import yxinfo.core.framework.util.transform.ListTransformer;
import yxinfo.core.framework.util.transform.Transformer;
import yxinfo.core.service.ou.OrgAppService;
import yxinfo.core.service.ou.dao.mapper.SysOrgAppMapper;
import yxinfo.core.service.ou.dao.model.SysOrgApp;
import yxinfo.core.service.ou.dao.model.SysOrgAppExample;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dy on 2017/6/28.
 */
@Component
@Service()
public class OrgAppServiceImpl implements OrgAppService {

    @Resource
    private SysOrgAppMapper orgAppMapper;

    public List<String> getAppByOrgId( Integer orgId ) {
        if ( orgId == null ) {
            return null;
        }
        SysOrgAppExample example = new SysOrgAppExample();
        SysOrgAppExample.Criteria criteria = example.createCriteria();
        criteria.andOrgIdEqualTo( orgId );
        List<SysOrgApp> list = orgAppMapper.selectByExample( example );
        if ( !CollectionUtils.isEmpty( list ) ) {
            return new ListTransformer<SysOrgApp, String>().listTransform( list, new Transformer<SysOrgApp, String>() {
                public String transform( SysOrgApp orgApp ) {
                    return orgApp.getApp();
                }
            } );
        }
        return null;
    }

    public void addOrgApp( Integer orgId, String app ) {
        if ( checkOrgAppExist( orgId, app ) ) {
            return;
        }
        SysOrgApp add = new SysOrgApp();
        add.setOrgId( orgId );
        add.setApp( app );
        orgAppMapper.insertSelective( add );
    }

    private boolean checkOrgAppExist( Integer orgId, String app ) {
        SysOrgAppExample example = new SysOrgAppExample();
        SysOrgAppExample.Criteria criteria = example.createCriteria();
        criteria.andAppEqualTo( app );
        criteria.andOrgIdEqualTo( orgId );
        return orgAppMapper.countByExample( example ) > 0;
    }
}
