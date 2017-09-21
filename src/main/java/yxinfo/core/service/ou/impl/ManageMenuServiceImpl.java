package yxinfo.core.service.ou.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import yxinfo.core.common.dto.RequestMsg;
import yxinfo.core.service.ou.ManageMenuService;
import yxinfo.core.service.ou.dao.mapper.SysMenuMapper;
import yxinfo.core.service.ou.dao.model.SysMenu;
import yxinfo.core.service.ou.dao.model.SysMenuExample;
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
public class ManageMenuServiceImpl implements ManageMenuService {

    @Resource
    private SysMenuMapper menuMapper;

    /**
     * 拼装菜单结构
     *
     * @param manageMenus
     * @return
     */
    public static List<ManageMenuDTO> getMenuTree( List<SysMenu> manageMenus ) {
        List<ManageMenuDTO> retList = null;
        if ( !CollectionUtils.isEmpty( manageMenus ) ) {
            retList = new ArrayList<ManageMenuDTO>();
            Map<Integer, ManageMenuDTO> temp = new HashMap<Integer, ManageMenuDTO>();
            for ( SysMenu manageMenu : manageMenus ) {
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

    public ManageMenuDTO getManageMenuById( Integer id ) {
        return new ManageMenuDTO().toDTO( menuMapper.selectByPrimaryKey( id ) );
    }

    public List<ManageMenuDTO> getManageMenu( ManageMenuDTO data, RequestMsg req ) {
        if ( data == null ) {
            return null;
        }
        SysMenuExample example = new SysMenuExample();
        SysMenuExample.Criteria criteria = example.createCriteria();
        if ( data.getTermCode() != null ) {
            criteria.andTermCodeEqualTo( data.getTermCode() );
        }
        if ( data.getApp() != null ) {
            criteria.andAppEqualTo( data.getApp() );
        }
        List<SysMenu> manageMenus = menuMapper.selectByExample( example );
        return getMenuTree( manageMenus );
    }
}
