package yxinfo.core.service.ou.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import yxinfo.core.common.dto.RequestMsg;
import yxinfo.core.service.ou.ManageMenuService;
import yxinfo.core.service.ou.MemberMenuService;
import yxinfo.core.service.ou.MemberRoleService;
import yxinfo.core.service.ou.RoleManageMenuService;
import yxinfo.core.service.ou.dto.ManageMenuDTO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dy on 2017/6/28.
 */
@Component
@Service
public class MemberMenuServiceImpl implements MemberMenuService {

    @Resource
    private MemberRoleService memberRoleService;
    @Resource
    private RoleManageMenuService roleManageMenuService;
    @Resource
    private ManageMenuService manageMenuService;

    public List<ManageMenuDTO> getManageMenu( ManageMenuDTO data, RequestMsg req ) {
        if ( data == null ) {
            return null;
        }
        // 获取角色列表
        List<Integer> roleIds = memberRoleService.getMemberRoleIdByTerminal( req.getMemberId(), req.getOrgId(), req.getTerminal() );
        if ( CollectionUtils.isEmpty( roleIds ) ) {
            return null;
        }
        // 获取菜单列表
        List<ManageMenuDTO> manageMenus = new ArrayList<ManageMenuDTO>();
        for ( Integer roleId : roleIds ) {
            List<Integer> menuIds = roleManageMenuService.getManageMenuIdByRoleId( roleId, data.getApp(), req.getTerminal() );
            if ( !CollectionUtils.isEmpty( menuIds ) ) {
                for ( Integer menuId : menuIds ) {
                    ManageMenuDTO manageMenu = manageMenuService.getManageMenuById( menuId );
                    // 过滤tab
                    if ( !StringUtils.isEmpty( data.getTab() ) ) {
                        if ( !data.getTab().equals( manageMenu.getTab() ) ) {
                            continue;
                        }
                    }
                    manageMenus.add( manageMenu );
                }
            }
        }
        // 拼装菜单结构
        List<ManageMenuDTO> retList = null;
        if ( !CollectionUtils.isEmpty( manageMenus ) ) {
            retList = new ArrayList<ManageMenuDTO>();
            Map<Integer, ManageMenuDTO> temp = new HashMap<Integer, ManageMenuDTO>();
            for ( ManageMenuDTO manageMenu : manageMenus ) {
                ManageMenuDTO menu, child;
                if ( manageMenu.getPid() == null ) {
                    menu = new ManageMenuDTO().toDTO( manageMenu );
                    retList.add( menu );
                    temp.put( manageMenu.getId(), menu );
                } else {
                    menu = temp.get( manageMenu.getPid() );
                    if ( menu == null ) {
                        menu = new ManageMenuDTO().toDTO( manageMenu );
                        retList.add( menu );
                        temp.put( menu.getId(), menu );
                    } else {
                        child = new ManageMenuDTO().toDTO( manageMenu );
                        List<ManageMenuDTO> childs = menu.getChilds();
                        if ( childs == null ) {
                            childs = new ArrayList<ManageMenuDTO>();
                            childs.add( child );
                            menu.setChilds( childs );
                        } else {
                            childs.add( child );
                        }
                    }
                }
            }
        }
        return retList;
    }
}
