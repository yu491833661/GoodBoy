package yxinfo.core.service.ou;

import yxinfo.core.common.dto.RequestMsg;
import yxinfo.core.common.route.Route;
import yxinfo.core.common.route.RouteCode;
import yxinfo.core.service.ou.dto.ManageMenuDTO;

import java.util.List;

/**
 * Created by dy on 2017/6/28.
 */
public interface MemberMenuService {

    /**
     * 获取用户管理后台菜单
     *
     * @param data
     * @param req
     * @return
     */
    @Route( code = RouteCode.Ou.MANAGE_MENU )
    List<ManageMenuDTO> getManageMenu( ManageMenuDTO data, RequestMsg req );
}
