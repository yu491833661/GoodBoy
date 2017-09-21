package yxinfo.core.service.ou;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import yxinfo.core.common.cache.CacheName;
import yxinfo.core.service.ou.dto.RoleMenuDTO;

import java.util.List;

/**
 * Created by dy on 2017/6/28.
 */
public interface RoleManageMenuService {

    /**
     * 根据角色id列表获取管理后台菜单id
     *
     * @param roleId
     * @param app
     * @param terminal
     * @return
     */
    @Cacheable( value = CacheName.Ou.Ou, key = "T(yxinfo.core.common.cache.CacheName.Ou).ROLE_MENU + '#' + 'mng' + #roleId + '#' + #app + '#' + #terminal" )
    List<Integer> getManageMenuIdByRoleId( Integer roleId, String app, String terminal );

    /**
     * 根据角色id列表删除角色与管理后台菜单的关系
     *
     * @param roleId
     * @param app
     * @param terminal
     */
    @CacheEvict( value = CacheName.Ou.Ou, key = "T(yxinfo.core.common.cache.CacheName.Ou).ROLE_MENU + '#' + 'mng' + #roleId + '#' + #app + '#' + #terminal" )
    void deleteManageMenuByRoleId( Integer roleId, String app, String terminal );

    /**
     * 根据角色id列表添加角色与管理后台菜单的关系
     *
     * @param roleMenus
     * @param roleId
     * @param app
     * @param terminal
     */
    @CacheEvict( value = CacheName.Ou.Ou, key = "T(yxinfo.core.common.cache.CacheName.Ou).ROLE_MENU + '#' + 'mng' + #roleId + '#' + #app + '#' + #terminal" )
    void addManageMenuByRoleId( List<RoleMenuDTO> roleMenus, Integer roleId, String app, String terminal );
}
