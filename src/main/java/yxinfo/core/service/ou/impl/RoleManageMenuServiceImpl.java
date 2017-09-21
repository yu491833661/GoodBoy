package yxinfo.core.service.ou.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import yxinfo.core.service.ou.RoleManageMenuService;
import yxinfo.core.service.ou.dao.mapper.SysRoleMenuMapper;
import yxinfo.core.service.ou.dao.mapper.SysRoleMenuMapperExtend;
import yxinfo.core.service.ou.dao.model.SysRoleMenu;
import yxinfo.core.service.ou.dto.RoleMenuDTO;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dy on 2017/6/28.
 */
@Component
@Service()
public class RoleManageMenuServiceImpl implements RoleManageMenuService {

    @Resource
    private SysRoleMenuMapper roleMenuMapper;
    @Resource
    private SysRoleMenuMapperExtend roleMenuMapperExtend;

    public List<Integer> getManageMenuIdByRoleId( Integer roleId, String app, String terminal ) {
        return roleMenuMapperExtend.selectMenuByRoleIdAndAppAndTerminal( roleId, app, terminal );
    }

    public void deleteManageMenuByRoleId( Integer roleId, String app, String terminal ) {
        if ( roleId == null ) {
            return;
        }
        roleMenuMapperExtend.deleteMenuByRoleIdAndAppAndTerminal( roleId, app, terminal );
    }

    public void addManageMenuByRoleId( List<RoleMenuDTO> roleMenus, Integer roleId, String app, String terminal ) {
        if ( CollectionUtils.isEmpty( roleMenus ) || roleId == null ) {
            return;
        }
        for ( RoleMenuDTO roleMenu : roleMenus ) {
            SysRoleMenu add = new SysRoleMenu();
            add.setMenuId( roleMenu.getMenuId() );
            add.setRoleId( roleId );
            add.setSort( roleMenu.getSort() == null ? 0 : roleMenu.getSort() );
            roleMenuMapper.insertSelective( add );
        }
    }
}
