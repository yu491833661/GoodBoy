package yxinfo.core.service.ou.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import yxinfo.core.service.ou.dao.model.SysMemberExample;
import yxinfo.core.service.ou.dao.model.SysMemberExtend;
import yxinfo.core.service.ou.dao.model.SysMemberOrgExample;

import java.util.List;

public interface SysMemberMapperExtend {

    /**
     * 搜索用户
     *
     * @param orgId
     * @param roleId
     * @param groupId
     * @param schText
     * @param example
     * @return
     */
    List<SysMemberExtend> searchMember( @Param( "orgId" ) Integer orgId, @Param( "roleId" ) Integer roleId, @Param( "groupId" ) Integer groupId,
                                        @Param( "schText" ) String schText, @Param( "example" ) SysMemberExample example );


    /**
     * 根据姓名与工号查询用户数量
     *
     * @param orgId
     * @param realName
     * @param fcode
     * @return
     */
    @Select( " SELECT COUNT(*) " +
            "  FROM sys_member m, sys_member_org mo" +
            "  WHERE mo.org_id = #{orgId} AND mo.member_id = m.id AND m.real_name = #{realName} AND m.fcode = #{fcode}" )
    int countByRealNameAndFcode( @Param( "orgId" ) Integer orgId, @Param( "realName" ) String realName, @Param( "fcode" ) String fcode );

    /**
     * 根据用户组与角色查询用户
     *
     * @param orgId
     * @param roleId
     * @param groupId
     * @param exampleMo
     * @param example
     * @return
     */
    List<SysMemberExtend> selectByGroupAndRole( @Param( "orgId" ) Integer orgId, @Param( "roleId" ) Integer roleId, @Param( "groupId" ) Integer groupId,
                                                @Param( "exampleMo" ) SysMemberOrgExample exampleMo, @Param( "example" ) SysMemberExample example );

    /**
     * 根据用户组查询用户
     *
     * @param orgId
     * @param groupId
     * @param exampleMo
     * @param example
     * @return
     */
    List<SysMemberExtend> selectByGroup( @Param( "orgId" ) Integer orgId, @Param( "groupId" ) Integer groupId,
                                         @Param( "exampleMo" ) SysMemberOrgExample exampleMo, @Param( "example" ) SysMemberExample example );

    /**
     * 根据角色查询用户
     *
     * @param orgId
     * @param roleId
     * @param exampleMo
     * @param example
     * @return
     */
    List<SysMemberExtend> selectByRole( @Param( "orgId" ) Integer orgId, @Param( "roleId" ) Integer roleId,
                                        @Param( "exampleMo" ) SysMemberOrgExample exampleMo, @Param( "example" ) SysMemberExample example );

    /**
     * 根据组织查询用户
     *
     * @param orgId
     * @param exampleMo
     * @param example
     * @return
     */
    List<SysMemberExtend> selectByOrg( @Param( "orgId" ) Integer orgId, @Param( "exampleMo" ) SysMemberOrgExample exampleMo, @Param( "example" ) SysMemberExample example );

    /**
     * 根据用户组与角色查询用户数量
     *
     * @param orgId
     * @param roleId
     * @param groupId
     * @param exampleMo
     * @param example
     * @return
     */
    int countByGroupAndRole( @Param( "orgId" ) Integer orgId, @Param( "roleId" ) Integer roleId, @Param( "groupId" ) Integer groupId,
                             @Param( "exampleMo" ) SysMemberOrgExample exampleMo, @Param( "example" ) SysMemberExample example );

    /**
     * 根据用户组查询用户数量
     *
     * @param orgId
     * @param groupId
     * @param exampleMo
     * @param example
     * @return
     */
    int countByGroup( @Param( "orgId" ) Integer orgId, @Param( "groupId" ) Integer groupId,
                      @Param( "exampleMo" ) SysMemberOrgExample exampleMo, @Param( "example" ) SysMemberExample example );

    /**
     * 根据角色查询用户数量
     *
     * @param orgId
     * @param roleId
     * @param exampleMo
     * @param example
     * @return
     */
    int countByRole( @Param( "orgId" ) Integer orgId, @Param( "roleId" ) Integer roleId,
                     @Param( "exampleMo" ) SysMemberOrgExample exampleMo, @Param( "example" ) SysMemberExample example );

    /**
     * 根据组织查询用户数量
     *
     * @param orgId
     * @param exampleMo
     * @param example
     * @return
     */
    int countByOrg( @Param( "orgId" ) Integer orgId, @Param( "exampleMo" ) SysMemberOrgExample exampleMo, @Param( "example" ) SysMemberExample example );
}
