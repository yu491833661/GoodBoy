package yxinfo.core.service.ou;

import java.util.List;

/**
 * Created by dy on 2016/8/13.
 */
public interface OrgAppService {

    /**
     * 根据组织id获取组织与应用关系
     *
     * @param orgId
     * @return
     */
    List<String> getAppByOrgId( Integer orgId );  // TODO cache

    /**
     * 建立组织与应用的关系
     *
     * @param orgId
     * @param app
     */
    void addOrgApp( Integer orgId, String app );
}
