package yxinfo.core.service.ou;

import java.util.List;

/**
 * Created by dy on 2016/8/13.
 */
public interface OrgTerminalService {

    /**
     * 检查组织与终端关系是否存在
     *
     * @param orgId
     * @param terminal
     * @return
     */
    boolean checkOrgTerminalExist( Integer orgId, String terminal );

    /**
     * 根据组织id获取组织与终端关系
     *
     * @param orgId
     * @return
     */
    List<String> getTerminalByOrgId( Integer orgId );
}
