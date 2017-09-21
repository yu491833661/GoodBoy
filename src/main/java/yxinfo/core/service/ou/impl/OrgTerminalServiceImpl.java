package yxinfo.core.service.ou.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import yxinfo.core.service.ou.AppTerminalService;
import yxinfo.core.service.ou.OrgAppService;
import yxinfo.core.service.ou.OrgTerminalService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by dy on 2017/6/28.
 */
@Component
@Service()
public class OrgTerminalServiceImpl implements OrgTerminalService {

    @Resource
    private AppTerminalService appTerminalService;
    @Resource
    private OrgAppService orgAppService;

    public boolean checkOrgTerminalExist( Integer orgId, String terminal ) {
        List<String> terminals = getTerminalByOrgId( orgId );
        if ( CollectionUtils.isEmpty( terminals ) ) {
            return false;
        }
        return terminals.contains( terminal );
    }

    public List<String> getTerminalByOrgId( Integer orgId ) {
        if ( orgId == null ) {
            return null;
        }
        List<String> apps = orgAppService.getAppByOrgId( orgId );
        if ( CollectionUtils.isEmpty( apps ) ) {
            return null;
        }
        Set<String> terminals = new TreeSet<String>();
        for ( String app : apps ) {
            List<String> ts = appTerminalService.getTerminalByApp( app );
            if ( !CollectionUtils.isEmpty( ts ) ) {
                terminals.addAll( ts );
            }
        }
        List<String> ret = new ArrayList<String>();
        if ( !CollectionUtils.isEmpty( terminals ) ) {
            ret.addAll( terminals );
        }
        return ret;
    }
}
