package yxinfo.core.service.ou.dao.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMenuMapperExtend {

    /**
     * 根据角色、app、终端编号查询菜单
     *
     * @param roleId
     * @param app
     * @param termCode
     * @return
     */
    List<Integer> selectMenuByRoleIdAndAppAndTerminal( @Param( "roleId" ) Integer roleId, @Param( "app" ) String app, @Param( "termCode" ) String termCode );

    /**
     * 根据角色、app、终端编号删除菜单与角色的关系
     *
     * @param roleId
     * @param app
     * @param termCode
     * @return
     */
    int deleteMenuByRoleIdAndAppAndTerminal( @Param( "roleId" ) Integer roleId, @Param( "app" ) String app, @Param( "termCode" ) String termCode );
}