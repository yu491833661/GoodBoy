package yxinfo.core.service.ou;

import org.springframework.cache.annotation.Cacheable;
import yxinfo.core.common.cache.CacheName;
import yxinfo.core.common.dto.RequestMsg;
import yxinfo.core.common.route.Route;
import yxinfo.core.common.route.RouteCode;
import yxinfo.core.service.ou.dto.ManageMenuDTO;

import java.util.List;

/**
 * Created by dy on 2017/6/28.
 */
public interface ManageMenuService {

    /**
     * 根据id获取管理后台菜单
     *
     * @param id
     * @return
     */
    @Cacheable( value = CacheName.Ou.Ou, key = "T(yxinfo.core.common.cache.CacheName.Ou).MANAGE_MENU + '#' + #id" )
    ManageMenuDTO getManageMenuById( Integer id );

    /**
     * 获取管理后台菜单
     * <p> 获取全部菜单 <p/>
     *
     * @param data
     * @param req
     * @return
     */
    @Route( code = RouteCode.Ou.MANAGE_MENU_LIST )
    List<ManageMenuDTO> getManageMenu( ManageMenuDTO data, RequestMsg req );
}
